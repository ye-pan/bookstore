package se.citerus.cqrs.bookstore.ordercontext.client.productcatalog;

import se.citerus.cqrs.bookstore.TransportObject;

public class BookDto extends TransportObject {
    public String bookId;
    public String isbn;
    public String title;
    public String description;
}
