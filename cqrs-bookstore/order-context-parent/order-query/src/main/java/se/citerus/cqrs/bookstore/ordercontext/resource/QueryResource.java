package se.citerus.cqrs.bookstore.ordercontext.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.event.DomainEventStore;
import se.citerus.cqrs.bookstore.ordercontext.query.QueryService;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderProjection;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("query")
public class QueryResource {
    private final QueryService queryService;
    private final DomainEventStore eventStore;

    public QueryResource(QueryService queryService, DomainEventStore eventStore) {
        this.queryService = queryService;
        this.eventStore = eventStore;
    }

    @GetMapping("events")
    public List<Object[]> getAllEvents() {
        List<DomainEvent<?>> allEvents = eventStore.getAllEvents();
        List<Object[]> eventsToReturn = new LinkedList<>();
        for (DomainEvent<?> event : allEvents) {
            eventsToReturn.add(new Object[]{event.getClass().getSigners(), event});
        }
        return eventsToReturn;
    }

    @GetMapping("orders")
    public Collection<OrderProjection> getOrders() {
        return queryService.getOrders();
    }

    @GetMapping("orders-per-day")
    public Map<LocalDate, Integer> getOrdersPerDay() {
        return queryService.getOrdersPerDay();
    }
}
