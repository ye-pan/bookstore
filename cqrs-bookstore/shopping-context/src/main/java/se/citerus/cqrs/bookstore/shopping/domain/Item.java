package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import java.math.BigDecimal;

@Data
public class Item extends ValueObject {
	private static final long serialVersionUID = 2695528005501926590L;
	private final ProductId productId;
	private final String title;
	private final BigDecimal price;
	
	public Item(ProductId productId, String title, BigDecimal price) {
		this.productId = productId;
		this.title = title;
		this.price = price;
	}
}
