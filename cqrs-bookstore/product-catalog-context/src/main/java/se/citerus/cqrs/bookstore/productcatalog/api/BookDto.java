package se.citerus.cqrs.bookstore.productcatalog.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

@Data
public class BookDto extends TransportObject {

	@NotEmpty
	private String isbn;
	
	@NotEmpty
	private String title;
	
	@NotNull
	private String description;
}
