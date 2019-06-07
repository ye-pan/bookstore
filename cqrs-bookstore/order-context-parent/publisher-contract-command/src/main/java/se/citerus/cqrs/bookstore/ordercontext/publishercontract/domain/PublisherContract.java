package se.citerus.cqrs.bookstore.ordercontext.publishercontract.domain;

import com.google.common.base.Preconditions;
import se.citerus.cqrs.bookstore.domain.AggregateRoot;
import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.event.PublisherContractRegisteredEvent;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.event.PurchaseRegisteredEvent;

import java.math.BigDecimal;

public class PublisherContract extends AggregateRoot<PublisherContractId> {
    private double feePercentage;
    private long limit;
    private BigDecimal accumulatedFee;

    public void register(PublisherContractId publisherContractId, String name, double feePercentage, long limit) {
        assertHasNotBeenRegistered();
        applyChange(new PublisherContractRegisteredEvent(publisherContractId, nextVersion(), now(), name, feePercentage, limit));
    }

    public void registerPurchase(ProductId productId, BigDecimal unitPrice, int quantity) {
        BigDecimal purchaseAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));
        AccumulatedFee newFee = new AccumulatedFee(accumulatedFee, limit, feePercentage).addPurchase(purchaseAmount);
        applyChange(new PurchaseRegisteredEvent(id(), nextVersion(), now(), productId, purchaseAmount, newFee.lastPurchaseFee(), newFee.accumulatedFee()));
    }

    private void assertHasNotBeenRegistered() {
        Preconditions.checkState(id() == null, "Contract has already been registered");
    }

    void handleEvent(PublisherContractRegisteredEvent event) {
        this.feePercentage = event.feePercentage;
        this.limit = event.limit;
        this.accumulatedFee = BigDecimal.ZERO;
    }

    void handleEvent(PurchaseRegisteredEvent event) {
        this.accumulatedFee = event.accumulatedFee;
    }
}
