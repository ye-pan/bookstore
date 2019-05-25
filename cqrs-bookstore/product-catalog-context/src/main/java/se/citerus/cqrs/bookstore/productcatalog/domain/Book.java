package se.citerus.cqrs.bookstore.productcatalog.domain;

import lombok.Getter;
import lombok.ToString;
import se.citerus.cqrs.bookstore.domain.ValueObject;

@ToString
@Getter
public class Book extends ValueObject {
	private static final long serialVersionUID = -2111539661804360768L;
	private String bookId;
	private String isbn;
	private String title;
	private String description;

	public Book() {

	}

	public Book(String bookId, String isbn, String title, String description) {
		this.bookId = bookId;
		this.isbn = isbn;
		this.title = title;
		this.description = description;
	}


}
