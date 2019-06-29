package se.citerus.cqrs.bookstore.productcatalog.api;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

import java.math.BigDecimal;

@Data
public class ProductDto extends TransportObject {

	private String productId;

	@Valid
	private BookDto book;

	@Min(1)
	@Digits(integer = 9, fraction = 2)
	private BigDecimal price;
	
	@NotEmpty
	private String publisherContractId;
}
