package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class ProductId extends ValueObject {
	private static final long serialVersionUID = 846932255214297714L;

	@Column
	private String productId;

	public ProductId() {

	}

	public ProductId(String productId) {
		this.productId = productId;
	}
}
