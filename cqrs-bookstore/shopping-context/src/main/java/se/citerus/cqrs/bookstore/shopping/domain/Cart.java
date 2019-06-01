package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Data
@Entity
@Table(name = "cart")
@EntityListeners(AuditingEntityListener.class)
public class Cart {

	@Id
	private String id;

	@OneToMany(targetEntity = LineItem.class, cascade = CascadeType.ALL)
	@MapKeyClass(ProductId.class)
	@JoinColumn(name = "cart_id")
	private final Map<ProductId, LineItem> lineItems = new LinkedHashMap<>();

	@CreatedDate
	@Column
	private Date createTime;

	@LastModifiedDate
	@Column
	private Date lastUpdateTime;

	public Cart() {

	}

	public Cart(String id) {
		this.id = id;
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
