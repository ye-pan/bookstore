package se.citerus.cqrs.bookstore.ordercontext.api;

import se.citerus.cqrs.bookstore.GenericId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    @NotEmpty
    @Pattern(regexp = GenericId.ID_PATTERN)
    public String cartId;

    @Min(1)
    public BigDecimal totalPrice;

    @Min(1)
    public int totalQuantity;

    @NotNull
    @NotEmpty
    public List<LineItemDto> lineItems;
}
