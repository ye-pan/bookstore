package se.citerus.cqrs.bookstore.shopping.domain;

import java.util.UUID;

import se.citerus.cqrs.bookstore.domain.ValueObject;

public class ProductId extends ValueObject {
	private static final long serialVersionUID = 846932255214297714L;
	public final String id;
	
	public ProductId(String id) {
		this.id = id;
	}
	
	public static ProductId randomId() {
		return new ProductId(UUID.randomUUID().toString());
	}
}
