package se.citerus.cqrs.bookstore.event;

import java.util.List;

import se.citerus.cqrs.bookstore.GenericId;

public interface DomainEventBus {
	void publish(List<DomainEvent<? extends GenericId>> events);
	void republish(List<DomainEvent<? extends GenericId>> events);
	<T extends DomainEventListener> T register(T listener);
}
