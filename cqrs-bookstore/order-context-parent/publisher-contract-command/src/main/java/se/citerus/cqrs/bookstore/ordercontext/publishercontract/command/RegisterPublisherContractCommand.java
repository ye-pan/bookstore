package se.citerus.cqrs.bookstore.ordercontext.publishercontract.command;

import com.google.common.base.Preconditions;
import se.citerus.cqrs.bookstore.command.Command;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;

public class RegisterPublisherContractCommand extends Command {
    public final PublisherContractId publisherContractId;
    public final String publisherName;
    public final double feePercentage;
    public final long limit;

    public RegisterPublisherContractCommand(PublisherContractId publisherContractId, String publisherName, double feePercentage, long limit) {
        Preconditions.checkArgument(publisherContractId != null, "PublisherContractId cannot be null");
        Preconditions.checkArgument(publisherName != null, "PublisherName cannot be null");
        Preconditions.checkArgument(feePercentage > 0, "Fee must be a positive number");
        Preconditions.checkArgument(limit > 0, "Limit must be a positive number");
        this.publisherContractId = publisherContractId;
        this.publisherName = publisherName;
        this.feePercentage = feePercentage;
        this.limit = limit;
    }
}
