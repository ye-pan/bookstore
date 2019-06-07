package se.citerus.cqrs.bookstore.ordercontext.query.orderlist;

import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;

import java.math.BigDecimal;

public class OrderLineProjection {
    public ProductId productId;
    public String title;
    public int quantity;
    public BigDecimal unitPrice;
}
