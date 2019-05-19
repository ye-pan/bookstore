package se.citerus.cqrs.bookstore.shopping.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.TransportObject;

public class CreateCartRequest extends TransportObject {
	
	@NotEmpty
	@Pattern(regexp = GenericId.ID_PATTERN)
	public String cartId;
}
