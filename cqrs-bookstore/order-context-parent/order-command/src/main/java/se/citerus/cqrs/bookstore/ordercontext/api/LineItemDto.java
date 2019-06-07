package se.citerus.cqrs.bookstore.ordercontext.api;

import se.citerus.cqrs.bookstore.GenericId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class LineItemDto {

    @NotEmpty
    @Pattern(regexp = GenericId.ID_PATTERN)
    public String productId;

    @NotNull
    public String title;

    @Min(1)
    public BigDecimal price;

    @Min(1)
    public int quantity;

    @Min(1)
    public BigDecimal totalPrice;
}
