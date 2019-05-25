package se.citerus.cqrs.bookstore.productcatalog.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Book extends ValueObject {
	private static final long serialVersionUID = -2111539661804360768L;
	@Column
	private String isbn;
	@Column
	private String title;
	@Column
	private String description;

	public Book() {

	}

	public Book(String isbn, String title, String description) {
		this.isbn = isbn;
		this.title = title;
		this.description = description;
	}


}
