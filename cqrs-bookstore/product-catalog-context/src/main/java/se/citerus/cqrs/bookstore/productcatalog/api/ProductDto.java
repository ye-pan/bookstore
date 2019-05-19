package se.citerus.cqrs.bookstore.productcatalog.api;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import se.citerus.cqrs.bookstore.TransportObject;

public class ProductDto extends TransportObject {
	
	public String productId;
	
	@Valid
	public BookDto book;

	@Min(0)
	public long price;
	
	@NotEmpty
	public String publisherContractId;
}
