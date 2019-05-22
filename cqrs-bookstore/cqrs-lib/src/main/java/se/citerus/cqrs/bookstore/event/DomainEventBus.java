package se.citerus.cqrs.bookstore.event;

import java.util.List;

import se.citerus.cqrs.bookstore.GenericId;

public interface DomainEventBus {
	<ID extends GenericId> void publish(List<DomainEvent<ID>> events);
	<ID extends  GenericId> void republish(List<DomainEvent<ID>> events);
	<T extends DomainEventListener> T register(T listener);
}
