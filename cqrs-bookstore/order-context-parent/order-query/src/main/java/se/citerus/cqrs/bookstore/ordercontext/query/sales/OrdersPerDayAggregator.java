package se.citerus.cqrs.bookstore.ordercontext.query.sales;

import com.google.common.eventbus.Subscribe;
import se.citerus.cqrs.bookstore.event.DomainEventListener;
import se.citerus.cqrs.bookstore.ordercontext.order.event.OrderPlacedEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * Simple in memory aggregator counting placed order per day based on order timestamp.
 */
public class OrdersPerDayAggregator implements DomainEventListener {
    private final TreeMap<Long, Integer> orders = new TreeMap<>();

    @Subscribe
    public void handleEvent(OrderPlacedEvent event) {
        Integer ordersPerDay = orders.get(event.timestamp);
        orders.put(event.timestamp, nullSafeIncrease(ordersPerDay));
    }

    private Integer nullSafeIncrease(Integer integer) {
        return integer == null ? 1 : integer + 1;
    }

    @Override
    public boolean supportsReplay() {
        return true;
    }

    public Map<Long, Integer> getOrdersPerDay() {
        return orders;
    }
}
