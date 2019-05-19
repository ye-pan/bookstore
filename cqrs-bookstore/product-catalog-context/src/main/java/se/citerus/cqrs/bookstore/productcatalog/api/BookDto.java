package se.citerus.cqrs.bookstore.productcatalog.api;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import se.citerus.cqrs.bookstore.TransportObject;

public class BookDto extends TransportObject {
	
	public String bookId;
	
	@NotEmpty
	public String isbn;
	
	@NotEmpty
	public String title;
	
	@NotNull
	public String description;
}
