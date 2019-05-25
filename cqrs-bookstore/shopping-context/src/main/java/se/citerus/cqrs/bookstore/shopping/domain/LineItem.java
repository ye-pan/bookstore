package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import java.math.BigDecimal;

@Data
public class LineItem extends ValueObject {
	private static final long serialVersionUID = 7313181600561935150L;
	private final Item item;
	private int quantity;
	
	public LineItem(Item item) {
		this.item = item;
		this.quantity = 1;
	}
	
	public void increaseQuantity() {
		this.quantity++;
	}
	
	public void decreaseQuantity() {
		if(quantity > 0) {
			quantity--;
		}
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return item.getPrice();
	}
	
	public BigDecimal getTotalPrice() {

		return item.getPrice().multiply(BigDecimal.valueOf(quantity));
	}
	
	public Item getItem() {
		return item;
	}
}
