package se.citerus.cqrs.bookstore.shopping.service;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.citerus.cqrs.bookstore.shopping.api.AddItemRequest;
import se.citerus.cqrs.bookstore.shopping.api.CartDto;
import se.citerus.cqrs.bookstore.shopping.api.CartDtoFactory;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductCatalogClient;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductDto;
import se.citerus.cqrs.bookstore.shopping.domain.Cart;
import se.citerus.cqrs.bookstore.shopping.domain.CartRepository;
import se.citerus.cqrs.bookstore.shopping.domain.Item;
import se.citerus.cqrs.bookstore.shopping.domain.ProductId;


@Slf4j
@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductCatalogClient productCatalogClient;

    public CartService(CartRepository cartRepository, ProductCatalogClient productCatalogClient) {
        this.cartRepository = cartRepository;
        this.productCatalogClient = productCatalogClient;
    }

    @Transactional
    public CartDto addItem(String cartId, AddItemRequest addItemRequest) {
        Cart cart = cartRepository.getOne(cartId);
        log.debug("Got addItem request " + addItemRequest);
        ProductDto product = productCatalogClient.getProduct(addItemRequest.getProductId());
        Preconditions.checkArgument(product != null && product.getBook() != null, "不存在书籍");
        Item item = new Item(new ProductId(addItemRequest.getProductId()), product.getBook().getTitle(), product.getPrice());
        log.info("Adding item to cart: " + item);
        cart.add(item);
        return CartDtoFactory.fromCart(cart);
    }
}
