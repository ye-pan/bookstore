package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.TransportObject;

public class OrderLine extends TransportObject {
    public final ProductId productId;
    public final String title;
    public final int quantity;
    public final long unitPrice;

    public OrderLine(ProductId productId, String title, int quantity, long unitPrice) {
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
