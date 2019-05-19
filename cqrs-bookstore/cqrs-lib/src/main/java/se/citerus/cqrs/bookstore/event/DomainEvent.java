package se.citerus.cqrs.bookstore.event;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import se.citerus.cqrs.bookstore.GenericId;

public abstract class DomainEvent<T extends GenericId> implements Serializable {
	private static final long serialVersionUID = -414284631305104479L;
	public final T aggregateId;
	public final int version;
	public final long timestamp;
	
	protected DomainEvent(T aggregateId, int version, long timestamp) {
		this.aggregateId = aggregateId;
		this.version = version;
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
