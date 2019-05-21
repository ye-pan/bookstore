package se.citerus.cqrs.bookstore.ordercontext.publishercontract.command;

import com.google.common.base.Preconditions;
import se.citerus.cqrs.bookstore.command.Command;
import se.citerus.cqrs.bookstore.ordercontext.order.ProductId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;

public class RegisterPurchaseCommand extends Command {
    public final PublisherContractId publisherContractId;
    public final ProductId productId;
    public final long unitPrice;
    public final int quantity;

    public RegisterPurchaseCommand(PublisherContractId publisherContractId, ProductId productId, long unitPrice, int quantity) {
        Preconditions.checkArgument(publisherContractId != null, "PublisherContractId cannot be null");
        Preconditions.checkArgument(productId != null, "ProductId cannot be null");
        Preconditions.checkArgument(unitPrice > 0, "UnitPrice must be a positive number");
        Preconditions.checkArgument(quantity > 0, "Quantity must be positive number");
        this.publisherContractId = publisherContractId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
