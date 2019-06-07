package se.citerus.cqrs.bookstore.ordercontext.order.domain;

import se.citerus.cqrs.bookstore.domain.ValueObject;
import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;

import java.math.BigDecimal;

public class OrderLine extends ValueObject {
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
