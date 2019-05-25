package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

@Data
public class ProductId extends ValueObject {
	private static final long serialVersionUID = 846932255214297714L;
	private final String productId;
	
	public ProductId(String productId) {
		this.productId = productId;
	}
}
