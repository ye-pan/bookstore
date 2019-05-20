package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.GenericId;

import java.util.UUID;

public class OrderId extends GenericId {
    public OrderId(String id) {
        super(id);
    }

    public static OrderId randomId() {
        return new OrderId(UUID.randomUUID().toString());
    }
}
