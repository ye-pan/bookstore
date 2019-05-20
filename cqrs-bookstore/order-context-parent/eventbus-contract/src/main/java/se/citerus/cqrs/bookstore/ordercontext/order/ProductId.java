package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.GenericId;

import java.util.UUID;

public class ProductId extends GenericId {
    public ProductId(String id) {
        super(id);
    }

    public static ProductId randomId() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
