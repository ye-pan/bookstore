package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Cart {
	private final String cartId;

	private final Map<ProductId, LineItem> lineItems = new LinkedHashMap<>();
	
	public Cart(String cartId) {
		this.cartId = cartId;
	}
	
	public void add(Item item) {
		LineItem lineItem = lineItems.get(item.getProductId());
		if(lineItem == null) {
			lineItem = new LineItem(item);
		} else {
			lineItem.increaseQuantity();
		}
		lineItems.put(item.getProductId(), lineItem);
	}
	
	public Collection<LineItem> getItems() {
		return Collections.unmodifiableCollection(lineItems.values());
	}
	
	public int getLineCount() {
		return lineItems.size();
	}
	
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(LineItem lineItem : lineItems.values()) {
			totalPrice = totalPrice.add(lineItem.getTotalPrice());
		}
		return totalPrice;
	}
	
	public int getTotalQuantity() {
		int totalQuantity = 0;
		for(LineItem lineItem : lineItems.values()) {
			totalQuantity += lineItem.getQuantity();
		}
		return totalQuantity;
	}
	
	public void remove(ProductId productId) {
		LineItem lineItem = lineItems.get(productId);
		if(lineItem != null) {
			lineItem.decreaseQuantity();
			if(lineItem.getQuantity() == 0) {
				lineItems.remove(productId);
			}
		}
	}
	
	public void removeAll(ProductId productId) {
		lineItems.remove(productId);
	}
}
