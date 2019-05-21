package se.citerus.cqrs.bookstore.ordercontext.api;

import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.TransportObject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterPublisherContractRequest extends TransportObject {

    @NotEmpty
    @Pattern(regexp = GenericId.ID_PATTERN)
    public String publisherContractId;

    @NotNull
    public String publisherName;

    @Min(1)
    public double feePercentage;

    @Min(1)
    public long limit;
}
