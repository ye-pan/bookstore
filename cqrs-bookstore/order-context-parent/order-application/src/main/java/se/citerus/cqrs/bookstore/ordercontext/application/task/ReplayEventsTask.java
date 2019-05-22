package se.citerus.cqrs.bookstore.ordercontext.application.task;

import se.citerus.cqrs.bookstore.event.DomainEventBus;
import se.citerus.cqrs.bookstore.event.DomainEventStore;

public class ReplayEventsTask {
    private final DomainEventStore domainEventStore;
    private final DomainEventBus domainEventBus;

    public ReplayEventsTask(DomainEventStore domainEventStore, DomainEventBus domainEventBus) {
        this.domainEventStore = domainEventStore;
        this.domainEventBus = domainEventBus;
    }

    public void execute() {
        domainEventBus.republish(domainEventStore.getAllEvents());
    }
}
