package se.citerus.cqrs.bookstore.productcatalog.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

@Data
public class Book extends ValueObject {
	private static final long serialVersionUID = -2111539661804360768L;
	private final String isbn;
	private final String title;
	private final String description;

	public Book(String isbn, String title, String description) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
	}


}
