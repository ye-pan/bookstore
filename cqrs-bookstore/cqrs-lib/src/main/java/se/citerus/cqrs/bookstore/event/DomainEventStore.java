package se.citerus.cqrs.bookstore.event;

import java.util.List;

import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.domain.AggregateRoot;

public interface DomainEventStore {
	<ID extends GenericId> List<DomainEvent<ID>> loadEvents(ID id);
	<ID extends GenericId> void save(ID id, Class<? extends AggregateRoot> type, List<DomainEvent<ID>> events);
	<ID extends GenericId> List<DomainEvent<ID>> getAllEvents();
}
