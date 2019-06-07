package se.citerus.cqrs.bookstore.ordercontext.publishercontract.domain;

import se.citerus.cqrs.bookstore.domain.ValueObject;

import java.math.BigDecimal;

public class AccumulatedFee extends ValueObject {
    private static final BigDecimal PER_CENT_DIVISOR = new BigDecimal(100);
    private static final BigDecimal TO_FROM_CENTS = new BigDecimal(100);
    private final BigDecimal accumulatedFee;
    private final BigDecimal limit;
    private final BigDecimal percentageMulitiplier;
    private final BigDecimal feePercentage;
    private final Fee lastPurchaseFee;

    public AccumulatedFee(BigDecimal accumulatedFee, long limit, double feePercentage) {
        this(accumulatedFee.divide(TO_FROM_CENTS), new BigDecimal(limit).divide(TO_FROM_CENTS), new BigDecimal(feePercentage), Fee.ZERO);
    }

    public AccumulatedFee(BigDecimal accumulatedFee, BigDecimal limit, BigDecimal feePercentage, Fee lastPurchaseFee) {
        this.accumulatedFee = accumulatedFee;
        this.limit = limit;
        this.feePercentage = feePercentage;
        this.percentageMulitiplier = feePercentage.divide(PER_CENT_DIVISOR);
        this.lastPurchaseFee = lastPurchaseFee;
    }

    public AccumulatedFee addPurchase(BigDecimal purchaseAmount) {
        if(limitReached()) {
            return new AccumulatedFee(accumulatedFee, limit, feePercentage, Fee.ZERO);
        } else {
            return getFee(purchaseAmount);
        }
    }

    private AccumulatedFee getFee(BigDecimal purchaseAmount) {
        BigDecimal feeAmount = purchaseAmount.divide(TO_FROM_CENTS).multiply(percentageMulitiplier);
        BigDecimal newAccumulatedFee = accumulatedFee.add(feeAmount);
        if(exceedsLimit(newAccumulatedFee)) {
            return new AccumulatedFee(limit, limit, feePercentage, new Fee(limit.subtract(accumulatedFee).doubleValue()));
        } else {
            return new AccumulatedFee(newAccumulatedFee, limit, feePercentage, new Fee(newAccumulatedFee.subtract(accumulatedFee).doubleValue()));
        }
    }

    public BigDecimal lastPurchaseFee() {
        return lastPurchaseFee.feeAmount().multiply(TO_FROM_CENTS);
    }

    public BigDecimal accumulatedFee() {
        return accumulatedFee.multiply(TO_FROM_CENTS);
    }

    private boolean exceedsLimit(BigDecimal add) {
        return add.compareTo(limit) >= 0;
    }

    private boolean limitReached() {
        return accumulatedFee.compareTo(limit) >= 0;
    }
}
