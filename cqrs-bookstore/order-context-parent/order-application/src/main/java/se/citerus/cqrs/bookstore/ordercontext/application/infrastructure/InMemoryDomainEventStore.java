package se.citerus.cqrs.bookstore.ordercontext.application.infrastructure;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.domain.AggregateRoot;
import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.event.DomainEventStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryDomainEventStore implements DomainEventStore {
    private final List<DomainEvent<? extends GenericId>> events = new ArrayList<>();

    @Override
    public synchronized <ID extends GenericId> List<DomainEvent<ID>> loadEvents(ID id) {
        List<DomainEvent<ID>> loadedEvents = new ArrayList<>();
        for (DomainEvent event : events) {
            if(event.aggregateId.equals(id)) {
                loadedEvents.add(event);
            }
        }
        if(loadedEvents.isEmpty())
            throw new IllegalArgumentException("Aggregate does not exist: " + id);
        else
            return loadedEvents;
    }

    @Override
    public synchronized <ID extends GenericId> void save(ID id, Class<? extends AggregateRoot> type, List<DomainEvent<ID>> domainEvents) {
        events.addAll(events);
    }

    @Override
    public synchronized <ID extends GenericId> List<DomainEvent<ID>> getAllEvents() {
        List<DomainEvent<ID>> domainEvents = new ArrayList<>();
        for (DomainEvent<? extends GenericId> event : events) {
            domainEvents.add((DomainEvent<ID>)event);
        }
        Collections.reverse(domainEvents);
        return domainEvents;
    }
}
