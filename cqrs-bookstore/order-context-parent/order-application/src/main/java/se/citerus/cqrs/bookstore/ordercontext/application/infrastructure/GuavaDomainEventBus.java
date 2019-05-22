package se.citerus.cqrs.bookstore.ordercontext.application.infrastructure;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.event.DomainEventBus;
import se.citerus.cqrs.bookstore.event.DomainEventListener;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class GuavaDomainEventBus implements DomainEventBus {

    private final EventBus eventBus;
    private final EventBus replayBus;

    public GuavaDomainEventBus() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        eventBus = new AsyncEventBus("eventbus", executorService);
        replayBus = new AsyncEventBus("replaybus", executorService);
    }
    @Override
    public <ID extends GenericId> void publish(List<DomainEvent<ID>> domainEvents) {
        for (DomainEvent<ID> domainEvent : domainEvents) {
            eventBus.post(domainEvent);
        }
    }

    @Override
    public <ID extends GenericId> void republish(List<DomainEvent<ID>> domainEvents) {
        log.info("Replaying [{}] events", domainEvents.size());
        for (DomainEvent<ID> domainEvent : domainEvents) {
            replayBus.post(domainEvent);
        }
    }

    @Override
    public <T extends DomainEventListener> T register(T listener) {
        if(listener.supportsReplay()) {
            replayBus.register(listener);
        }
        eventBus.register(listener);
        return  listener;
    }
}
