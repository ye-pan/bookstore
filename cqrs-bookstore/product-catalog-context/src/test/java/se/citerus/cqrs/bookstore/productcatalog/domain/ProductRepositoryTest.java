package se.citerus.cqrs.bookstore.productcatalog.domain;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSave() {
        Book book = new Book("978-7-121-2448-5", "实现领域驱动设计", "DDD");
        Product product = new Product(book, new BigDecimal("99.00"), "Vaughn Vernon");
        productRepository.save(product);
    }

    @Test
    public void testFindAll() {
        Collection<Product> products = productRepository.findAll();
        assertNotNull(products);
        log.info("products {}", products);
    }
}