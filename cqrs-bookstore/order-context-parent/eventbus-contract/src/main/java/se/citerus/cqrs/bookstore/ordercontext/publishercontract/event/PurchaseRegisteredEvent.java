package se.citerus.cqrs.bookstore.ordercontext.publishercontract.event;

import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;

public class PurchaseRegisteredEvent extends DomainEvent<PublisherContractId> {
    public final ProductId productId;
    public final long purchaseAmount;
    public final long feeAmount;
    public final long accumulatedFee;

    public PurchaseRegisteredEvent(PublisherContractId aggregateId, int version, long timestamp, ProductId productId, long purchaseAmount, long feeAmount, long accumulatedFee) {
        super(aggregateId, version, timestamp);
        this.productId = productId;
        this.purchaseAmount = purchaseAmount;
        this.feeAmount = feeAmount;
        this.accumulatedFee = accumulatedFee;
    }
}
