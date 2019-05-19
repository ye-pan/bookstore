package se.citerus.cqrs.bookstore.productcatalog.domain;

import se.citerus.cqrs.bookstore.domain.ValueObject;

public class Product extends ValueObject {
	private static final long serialVersionUID = 3138763299924626727L;
	public final String productId;
	public final Book book;
	public final long price;
	public final String publisherContractId;
	
	public Product(String productId, Book book, long price, String publisherContractId) {
		this.productId = productId;
		this.book = book;
		this.price = price;
		this.publisherContractId = publisherContractId;
	}
}
