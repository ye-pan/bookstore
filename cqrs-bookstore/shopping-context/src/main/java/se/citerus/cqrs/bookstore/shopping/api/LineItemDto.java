package se.citerus.cqrs.bookstore.shopping.api;

import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

import java.math.BigDecimal;

@Data
public class LineItemDto extends TransportObject {
	private String productId;
	private String title;
	private BigDecimal price;
	private int quantity;
	private BigDecimal totalPrice;
}
