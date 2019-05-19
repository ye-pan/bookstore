package se.citerus.cqrs.bookstore.shopping.api;

import java.util.List;

import se.citerus.cqrs.bookstore.TransportObject;

public class CartDto extends TransportObject {
	public String cartId;
	public long totalPrice;
	public int totalQuantity;
	public List<LineItemDto> lineItems;
}
