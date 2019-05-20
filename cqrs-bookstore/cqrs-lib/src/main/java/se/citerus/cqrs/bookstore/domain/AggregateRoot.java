package se.citerus.cqrs.bookstore.domain;

import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.event.DomainEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AggregateRoot<T extends GenericId> {
    private final List<DomainEvent<T>> uncommittedEvents = new ArrayList<>();

    private T id;
    private int version = 0;
    private long timestamp = 0;
    public List<DomainEvent<T>> getUncommittedEvents() {
        return uncommittedEvents;
    }

    public void loadFromHistory(List<DomainEvent<T>> history) {
        for (DomainEvent<T> event : history) {
            applyChange(event, false);
        }
    }

    protected int nextVersion() {
        return version + 1;
    }

    protected long now() {
        return System.currentTimeMillis();
    }

    public T id() {
        return id;
    }

    public int version() {
        return version;
    }

    public long timestamp() {
        return timestamp;
    }

    protected void applyChange(DomainEvent<T> event) {
        applyChange(event, true);
    }

    private void applyChange(DomainEvent<T> event, boolean isNew) {
        updateMetadata(event);
        invokeHandlerMethod(event);
        if(isNew)
            uncommittedEvents.add(event);
    }

    private void updateMetadata(DomainEvent<T> event) {
        this.id = event.aggregateId;
        this.version = event.version;
        this.timestamp = event.timestamp;
    }

    private void invokeHandlerMethod(DomainEvent<T> event) {
        Method handlerMethod = getHandlerMethod(event);
        if(handlerMethod != null) {
            handlerMethod.setAccessible(true);
            try {
                handlerMethod.invoke(this, event);
            } catch(Exception e) {
                throw new RuntimeException("Unable to call event handler method for " + event.getClass().getName(), e);
            }
        }
    }

    private Method getHandlerMethod(DomainEvent<T> event) {
        try {
            return getClass().getDeclaredMethod("handleEvent", event.getClass());
        } catch(NoSuchMethodException e) {
            return null;
        }
    }

    public boolean hasUncommittedEvents() {
        return !uncommittedEvents.isEmpty();
    }
}
