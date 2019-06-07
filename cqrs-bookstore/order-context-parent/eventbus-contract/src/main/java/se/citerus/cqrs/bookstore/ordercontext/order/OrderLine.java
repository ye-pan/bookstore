package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.TransportObject;

import java.math.BigDecimal;

public class OrderLine extends TransportObject {
    public final ProductId productId;
    public final String title;
    public final int quantity;
    public final BigDecimal unitPrice;

    public OrderLine(ProductId productId, String title, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
