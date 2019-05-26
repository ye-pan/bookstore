package se.citerus.cqrs.bookstore.shopping.controller;

import javax.validation.Valid;

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
import se.citerus.cqrs.bookstore.TransportObject;
import se.citerus.cqrs.bookstore.api.ApiMessageDto;
import se.citerus.cqrs.bookstore.shopping.api.AddItemRequest;
import se.citerus.cqrs.bookstore.shopping.api.CartDto;
import se.citerus.cqrs.bookstore.shopping.api.CartDtoFactory;
import se.citerus.cqrs.bookstore.shopping.api.CreateCartRequest;
import se.citerus.cqrs.bookstore.shopping.domain.Cart;
import se.citerus.cqrs.bookstore.shopping.domain.CartRepository;
import se.citerus.cqrs.bookstore.shopping.service.CartService;


@RestController
@RequestMapping(path = "carts")
public class CartController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final CartService cartService;
	private final CartRepository cartRepository;
	
	public CartController(CartRepository cartRepository, CartService cartService) {
		this.cartRepository = cartRepository;
		this.cartService = cartService;
	}

	@PostMapping
	public ResponseEntity<ApiMessageDto> initCart(@RequestBody @Valid CreateCartRequest cart) {
		cartRepository.save(new Cart(cart.getCartId()));
		return ResponseEntity.ok(new ApiMessageDto("操作成功!"));
	}

	@PostMapping("{cartId}/items")
	public CartDto addItem(@PathVariable("cartId") String cartId, @RequestBody AddItemRequest addItemRequest) {
		return cartService.addItem(cartId, addItemRequest);
	}

	@GetMapping("{cartId}")
	public ResponseEntity<TransportObject> getCart(@PathVariable("cartId") String cartId) {
		Cart cart = cartRepository.getOne(cartId);
		if(cart == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiMessageDto(String.format("Cart with id '%s' does not exist", cartId)));
		} else {
			logger.info("Returning cart with [{}] lines", cart.getLineCount());
			return ResponseEntity.ok().body(CartDtoFactory.fromCart(cart));
		}
	}
	
	@DeleteMapping("{cartId}")
	public void deleteCart(@PathVariable("cartId") String cartId) {
		cartRepository.deleteById(cartId);
		logger.info("Shopping cart for session [{}] cleared", cartId);
	}
}
