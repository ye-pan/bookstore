package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.citerus.cqrs.bookstore.shopping.api.AddItemRequest;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.BookDto;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductCatalogClient;
import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductDto;
import se.citerus.cqrs.bookstore.shopping.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Mock
    private ProductCatalogClient productCatalogClient;

    private ProductDto product;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BookDto book = new BookDto();
        book.setIsbn("978-7-111-07566-0");
        book.setTitle("TCP/IP详解");
        product = new ProductDto();
        product.setBook(book);
        product.setPrice(BigDecimal.valueOf(45));

    }
    @Test
    public void testSave() {
        Item item = new Item(new ProductId(UUID.randomUUID().toString().replaceAll("-", "")), "企业应用架构模式", BigDecimal.valueOf(59D));
        Cart cart = new Cart();
        cart.add(item);
        cartRepository.save(cart);
    }

    @Test
    public void testTestFindAll() {
        List<Cart> carts = cartRepository.findAll();
        assertTrue(carts.size() == 0);
        carts.forEach((c)->{log.info("cart {}", c);});
    }

    @Test
    public void testSave2() {
        Mockito.when(productCatalogClient.getProduct("297e56096aef687b016aef6883dc0000")).thenReturn(product);
        AddItemRequest request = new AddItemRequest();
        request.setProductId("297e56096aef687b016aef6883dc0000");
        cartService.addItem("297e56096af15368016af15372700000", request);
    }
}