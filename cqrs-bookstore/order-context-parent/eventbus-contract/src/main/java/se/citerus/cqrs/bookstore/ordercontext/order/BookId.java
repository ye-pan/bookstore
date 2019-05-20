package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.GenericId;

import java.util.UUID;

public class BookId extends GenericId {
    public BookId(String id) {
        super(id);
    }

    public static BookId randomId() {
        return new BookId(UUID.randomUUID().toString());
    }
}
