package se.citerus.cqrs.bookstore.ordercontext.query.orderlist;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import se.citerus.cqrs.bookstore.event.DomainEventListener;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderId;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderLine;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderStatus;
import se.citerus.cqrs.bookstore.ordercontext.order.event.OrderActivatedEvent;
import se.citerus.cqrs.bookstore.ordercontext.order.event.OrderPlacedEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * Listens to order events and stores projections of orders and their status as read models in the
 * {@link OrderProjectionRepository}. Supports retrieving lists of orders as well as individual orders by id.
 */
@Slf4j
public class OrderListDenormalizer implements DomainEventListener {

    private final OrderProjectionRepository repository;

    public OrderListDenormalizer(OrderProjectionRepository repository) {
        this.repository = repository;
    }

    @Subscribe
    public void handleEvent(OrderPlacedEvent event) {
        log.info("Received: " + event.toString());
        List<OrderLineProjection> orderLines = new LinkedList<>();
        for (OrderLine orderLine : event.orderLines) {
            OrderLineProjection line = new OrderLineProjection();
            line.productId = orderLine.productId;
            line.quantity = orderLine.quantity;
            line.title = orderLine.title;
            line.unitPrice = orderLine.unitPrice;
            orderLines.add(line);
        }

        OrderProjection orderProjection = new OrderProjection(event.aggregateId, event.timestamp, event.customerInformation.customerName, event.orderAmount, orderLines, OrderStatus.PLACED);
        repository.save(orderProjection);
    }

    @Subscribe
    public void handleEvent(OrderActivatedEvent event) {
        log.info("Received: " + event.toString());
        OrderProjection orderProjection = repository.getById(event.aggregateId);
        orderProjection.setStatus(OrderStatus.ACTIVATED);
    }

    public List<OrderProjection> getOrders() {
        return repository.listOrdersByTimestamp();
    }

    public OrderProjection get(OrderId orderId) {
        return repository.getById(orderId);
    }

    @Override
    public boolean supportsReplay() {
        return true;
    }
}
