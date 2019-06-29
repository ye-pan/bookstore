package se.citerus.cqrs.bookstore.productcatalog.domain;

import java.util.List;

public interface ProductRepository {

    Product get(String productId);

    void save(Product product);

    List<Product> findAll();
}
