package se.citerus.cqrs.bookstore.shopping.resource;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.citerus.cqrs.bookstore.api.ApiMessageDto;
import se.citerus.cqrs.bookstore.shopping.api.AddItemRequest;
import se.citerus.cqrs.bookstore.shopping.api.CartDto;
import se.citerus.cqrs.bookstore.shopping.api.CartDtoFactory;
import se.citerus.cqrs.bookstore.shopping.api.CreateCartRequest;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductCatalogClient;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductDto;
import se.citerus.cqrs.bookstore.shopping.domain.Cart;
import se.citerus.cqrs.bookstore.shopping.domain.CartRepository;
import se.citerus.cqrs.bookstore.shopping.domain.Item;
import se.citerus.cqrs.bookstore.shopping.domain.ProductId;


@RestController
@RequestMapping(path = "carts")
public class CartResource {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final ProductCatalogClient productCatalogClient;
	
	private final CartRepository cartRepository;
	
	public CartResource(ProductCatalogClient productCatalogClient, CartRepository cartRepository) {
		this.productCatalogClient = productCatalogClient;
		this.cartRepository = cartRepository;
	}

	@PostMapping
	public ResponseEntity<ApiMessageDto> initCart(@RequestBody @Valid CreateCartRequest cart) {
		cartRepository.save(new Cart(cart.cartId));
		return ResponseEntity.ok(new ApiMessageDto("操作成功!"));
	}

	@PostMapping("{cartId}/items")
	public CartDto addItem(@PathVariable("cartId") String cartId, @RequestBody AddItemRequest addItemRequest) {
		Cart cart = cartRepository.get(cartId);
		logger.debug("Got addItem request " + addItemRequest);
		ProductDto product = productCatalogClient.getProduct(addItemRequest.productId);
		assertProductExists(addItemRequest.productId, product);
		Item item = new Item(new ProductId(addItemRequest.productId), product.book.title, product.price);
		logger.info("Adding item to cart: " + item);
		cart.add(item);
		return CartDtoFactory.fromCart(cart);
	}
	
	@GetMapping("{cartId}")
	public ResponseEntity<CartDto> getCart(@PathVariable("cartId") String cartId) {
		Cart cart = cartRepository.find(cartId);
		if(cart == null) {
			//TODO (String.format("Cart with id '%s' does not exist", cartId));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			logger.info("Returning cart with [{}] lines", cart.getLineCount());
			return ResponseEntity.ok().body(CartDtoFactory.fromCart(cart));
		}
	}
	
	@DeleteMapping("{cartId}")
	public void deleteCart(@PathVariable("cartId") String cartId) {
		cartRepository.delete(cartId);
		logger.info("Shopping cart for session [{}] cleared", cartId);
	}

	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAllCart() {
		cartRepository.clear();
		return ResponseEntity.ok("操作成功!");
	}

	private void assertProductExists(String productId, ProductDto product) {
		if(product == null) {
			//TODO throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Product with id '" + productId + "' could not be found").build());
			throw new NullPointerException();
		}
	}
}
