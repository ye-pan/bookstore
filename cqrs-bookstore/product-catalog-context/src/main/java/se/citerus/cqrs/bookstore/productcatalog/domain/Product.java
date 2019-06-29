package se.citerus.cqrs.bookstore.productcatalog.domain;

import lombok.Data;
import se.citerus.cqrs.bookstore.domain.ValueObject;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product extends ValueObject {
	private static final long serialVersionUID = 3138763299924626727L;

	private String id;
	private Book book;
	private BigDecimal price;
	private String publisherContractId;
	private Date createTime;
	private Date lastUpdateTime;
	public Product() {

	}

	public Product(Book book, BigDecimal price, String publisherContractId) {
		this.book = book;
		this.price = price;
		this.publisherContractId = publisherContractId;
	}
}
