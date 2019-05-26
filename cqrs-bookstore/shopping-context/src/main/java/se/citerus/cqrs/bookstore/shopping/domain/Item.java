package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.math.BigDecimal;

@Embeddable
@Data
public class Item extends ValueObject {
	private static final long serialVersionUID = 2695528005501926590L;

	@Embedded
	private ProductId productId;
	@Column
	private String title;
	@Column
	private BigDecimal price;

	private Item() {

	}
	public Item(ProductId productId, String title, BigDecimal price) {
		this.productId = productId;
		this.title = title;
		this.price = price;
	}
}
