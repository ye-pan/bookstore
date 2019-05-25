package se.citerus.cqrs.bookstore.shopping.client.productcatalog;

import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

@Data
public class BookDto extends TransportObject {
	private String title;
	private String isbn;
}
