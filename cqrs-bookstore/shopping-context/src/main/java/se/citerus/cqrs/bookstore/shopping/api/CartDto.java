package se.citerus.cqrs.bookstore.shopping.api;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

@Data
public class CartDto extends TransportObject {
	private String cartId;
	private BigDecimal totalPrice;
	private int totalQuantity;
	private List<LineItemDto> lineItems;
}
