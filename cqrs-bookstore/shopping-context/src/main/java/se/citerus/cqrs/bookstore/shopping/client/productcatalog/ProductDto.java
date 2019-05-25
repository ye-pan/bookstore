package se.citerus.cqrs.bookstore.shopping.client.productcatalog;


import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

import java.math.BigDecimal;


@Data
public class ProductDto extends TransportObject {
	private BookDto book;
	private BigDecimal price;
}
