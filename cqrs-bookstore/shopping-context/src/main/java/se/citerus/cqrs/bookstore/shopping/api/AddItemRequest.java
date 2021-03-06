package se.citerus.cqrs.bookstore.shopping.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;
import se.citerus.cqrs.bookstore.GenericId;
import se.citerus.cqrs.bookstore.TransportObject;

@Data
public class AddItemRequest extends TransportObject {
	
	@NotEmpty
	@Pattern(regexp = GenericId.ID_PATTERN)
	private String productId;
}
