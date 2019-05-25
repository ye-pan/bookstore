package se.citerus.cqrs.bookstore.productcatalog.domain;

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
@Table(name = "product")
@GenericGenerator(name = "productId", strategy = "uuid")
@EntityListeners(AuditingEntityListener.class)
public class Product extends ValueObject {
	private static final long serialVersionUID = 3138763299924626727L;

	@Id
	@GeneratedValue(generator = "productId")
	private String id;
	@Embedded
	private Book book;
	@Column
	private BigDecimal price;
	@Column
	private String publisherContractId;
	@Column
	@CreatedDate
	private Date createTime;
	@Column
	@LastModifiedDate
	private Date lastUpdateTime;
	public Product() {

	}

	public Product(Book book, BigDecimal price, String publisherContractId) {
		this.book = book;
		this.price = price;
		this.publisherContractId = publisherContractId;
	}
}
