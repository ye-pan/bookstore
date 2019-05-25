package se.citerus.cqrs.bookstore.productcatalog.api;

import org.junit.Test;
import se.citerus.cqrs.bookstore.productcatalog.domain.Book;
import se.citerus.cqrs.bookstore.productcatalog.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductDtoFactoryTest {

    @Test(expected = NullPointerException.class)
    public void fromProduct() {
        ProductDtoFactory.fromProduct(null);
    }

    @Test(expected = NullPointerException.class)
    public void fromProduct2() {
        Product product = new Product();
        ProductDtoFactory.fromProduct(product);
    }

    @Test
    public void fromProduct3() {
        Book book = new Book("12344321", "junit test", "a junit test case");
        Product product = new Product(book, new BigDecimal(String.valueOf(55.55)), "1234431");
        ProductDto productDto = ProductDtoFactory.fromProduct(product);
        assertNotNull(productDto);
        assertNotNull(productDto.getBook());
        assertNotNull(product.getId());
        assertNotNull(product.getPrice());
        assertNotNull(product.getPublisherContractId());
        assertNotNull(product.getBook().getTitle());
        assertNotNull(product.getBook().getIsbn());
        assertNotNull(product.getBook().getDescription());
    }

}