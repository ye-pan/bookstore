package se.citerus.cqrs.bookstore.shopping.client.productcatalog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCatalogClientTest {

    @MockBean
    private ProductCatalogClient productCatalogClient;

    @Before
    public void setUp() {
        BookDto bookDto = new BookDto();
        ProductDto productDto = new ProductDto();
        productDto.setBook(bookDto);
        BDDMockito.given(productCatalogClient.getProduct("297e56096aef687b016aef6883dc0000"))
                .willReturn(productDto);
    }

    @Test
    public void getProduct() {
        ProductDto products = productCatalogClient.getProduct("297e56096aef687b016aef6883dc0000");
        assertNotNull(products);
        assertNotNull(products.getBook());
        log.debug("products {}", products);
    }
}