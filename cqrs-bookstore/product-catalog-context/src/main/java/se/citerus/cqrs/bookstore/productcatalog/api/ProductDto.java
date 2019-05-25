package se.citerus.cqrs.bookstore.productcatalog.api;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;
import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.TransportObject;

import java.math.BigDecimal;

@Data
public class ProductDto extends TransportObject {

	@NotEmpty
	@Pattern(regexp = GenericId.ID_PATTERN)
	private String productId;
	
	@Valid
	private BookDto book;

	@Min(1)
	private BigDecimal price;
	
	@NotEmpty
	private String publisherContractId;
}
