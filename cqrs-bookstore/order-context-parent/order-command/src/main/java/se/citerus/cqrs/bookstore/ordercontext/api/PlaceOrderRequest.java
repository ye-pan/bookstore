package se.citerus.cqrs.bookstore.ordercontext.api;

import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.TransportObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PlaceOrderRequest extends TransportObject {

    @NotEmpty
    @Pattern(regexp = GenericId.ID_PATTERN)
    public String orderId;

    @NotNull
    public String customerName;

    @NotNull
    public String customerEmail;

    @NotNull
    public String customerAddress;

    @NotNull
    public CartDto cart;
}
