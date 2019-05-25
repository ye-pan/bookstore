package se.citerus.cqrs.bookstore.productcatalog.domain;

import lombok.Getter;
import lombok.ToString;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import java.math.BigDecimal;

@Getter
@ToString
public class Product extends ValueObject {
	private static final long serialVersionUID = 3138763299924626727L;
	private String productId;
	private Book book;
	private BigDecimal price;
	private String publisherContractId;

	public Product() {

	}

	public Product(String productId, Book book, BigDecimal price, String publisherContractId) {
		this.productId = productId;
		this.book = book;
		this.price = price;
		this.publisherContractId = publisherContractId;
	}
}
