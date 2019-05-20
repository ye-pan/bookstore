package se.citerus.cqrs.bookstore.ordercontext.publishercontract.event;

import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;

public class PublisherContractRegisteredEvent extends DomainEvent<PublisherContractId> {
    public final String publisherName;
    public final double feePercentage;
    public final long limit;

    public PublisherContractRegisteredEvent(PublisherContractId aggregateId, int version, long timestamp, String publisherName, double feePercentage, long limit) {
        super(aggregateId, version, timestamp);
        this.publisherName = publisherName;
        this.feePercentage = feePercentage;
        this.limit = limit;
    }
}
