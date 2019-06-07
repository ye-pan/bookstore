package se.citerus.cqrs.bookstore.ordercontext.order.event;

import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.ordercontext.order.CustomerInformation;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderId;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderLine;

import java.math.BigDecimal;
import java.util.List;

public class OrderPlacedEvent extends DomainEvent<OrderId> {
    public final CustomerInformation customerInformation;
    public final List<OrderLine> orderLines;
    public final BigDecimal orderAmount;

    public OrderPlacedEvent(OrderId id, int version, long timestamp, CustomerInformation customerInformation, List<OrderLine> orderLines, BigDecimal orderAmount) {
        super(id, version, timestamp);
        this.customerInformation = customerInformation;
        this.orderLines = orderLines;
        this.orderAmount = orderAmount;
    }
}
