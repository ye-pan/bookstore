package se.citerus.cqrs.bookstore.ordercontext.client.productcatalog;

import se.citerus.cqrs.bookstore.TransportObject;

public class ProductDto extends TransportObject {
    public String productId;
    public BookDto book;
    public double price;
    public String publisherContractId;
}
