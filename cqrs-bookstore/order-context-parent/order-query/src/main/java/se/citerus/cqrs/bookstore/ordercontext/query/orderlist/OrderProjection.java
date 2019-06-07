package se.citerus.cqrs.bookstore.ordercontext.query.orderlist;

import se.citerus.cqrs.bookstore.ordercontext.order.OrderId;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderStatus;
import se.citerus.cqrs.bookstore.ordercontext.query.Projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderProjection extends Projection {
    private final OrderId orderId;
    private final long orderPlacedTimestamp;
    private final BigDecimal orderAmount;
    private final String customerName;
    private final List<OrderLineProjection> orderLines;
    private OrderStatus status;
    public OrderProjection(OrderId orderId, long orderPlacedTimestamp, String customerName, BigDecimal orderAmount, List<OrderLineProjection> orderLines, OrderStatus status) {
        this.orderId = orderId;
        this.orderPlacedTimestamp = orderPlacedTimestamp;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.orderLines = new ArrayList<>(orderLines);
        this.status = status;
    }

    public List<OrderLineProjection> getOrderLines() {
        return orderLines;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getOrderPlacedTimestamp() {
        return orderPlacedTimestamp;
    }
}
