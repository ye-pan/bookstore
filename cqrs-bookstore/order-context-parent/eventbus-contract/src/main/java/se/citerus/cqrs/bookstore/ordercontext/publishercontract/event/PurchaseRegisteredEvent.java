package se.citerus.cqrs.bookstore.ordercontext.publishercontract.event;

import se.citerus.cqrs.bookstore.event.DomainEvent;
import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;

import java.math.BigDecimal;

public class PurchaseRegisteredEvent extends DomainEvent<PublisherContractId> {
    public final ProductId productId;
    public final BigDecimal purchaseAmount;
    public final BigDecimal feeAmount;
    public final BigDecimal accumulatedFee;

    public PurchaseRegisteredEvent(PublisherContractId aggregateId, int version, long timestamp, ProductId productId,
                                   BigDecimal purchaseAmount, BigDecimal feeAmount, BigDecimal accumulatedFee) {
        super(aggregateId, version, timestamp);
        this.productId = productId;
        this.purchaseAmount = purchaseAmount;
        this.feeAmount = feeAmount;
        this.accumulatedFee = accumulatedFee;
    }
}
