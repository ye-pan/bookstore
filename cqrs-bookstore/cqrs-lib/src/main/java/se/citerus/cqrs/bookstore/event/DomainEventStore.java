package se.citerus.cqrs.bookstore.event;

import java.util.List;

import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.domain.AggregateRoot;

public interface DomainEventStore {
	List<DomainEvent<? extends GenericId>> loadEvents(GenericId id);
	void save(GenericId id, Class<? extends AggregateRoot<? extends GenericId>> type, List<DomainEvent<? extends GenericId>> events);
	List<DomainEvent<? extends GenericId>> getAllEvents();
}
