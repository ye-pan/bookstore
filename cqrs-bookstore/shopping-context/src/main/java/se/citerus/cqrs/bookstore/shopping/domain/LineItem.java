package se.citerus.cqrs.bookstore.shopping.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import se.citerus.cqrs.bookstore.domain.ValueObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "line_item")
@GenericGenerator(name = "lineItemId", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class LineItem extends ValueObject {
	private static final long serialVersionUID = 7313181600561935150L;

	@Id
	@GeneratedValue(generator = "lineItemId")
	private String id;

	@Embedded
	private Item item;

	@Column
	private int quantity;

	@CreatedDate
	@Column
	private Date createTime;

	@LastModifiedDate
	@Column
	private Date lastUpdateTime;

	public LineItem() {

	}

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
