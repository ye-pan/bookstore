package se.citerus.cqrs.bookstore.productcatalog.infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import se.citerus.cqrs.bookstore.productcatalog.domain.Product;
import se.citerus.cqrs.bookstore.productcatalog.domain.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	private final Map<String, Product> products = new HashMap<>();
	
	private static final Comparator<Product> PRODUCT_COMPARATOR = new Comparator<Product>() {
		public int compare(Product o1, Product o2) {
			return o1.book.title.compareTo(o2.book.title);
		}
	};

	@Override
	public Collection<Product> getProducts() {
		List<Product> values = new ArrayList<>(products.values());
		Collections.sort(values, PRODUCT_COMPARATOR);
		return values;
	}
	
	@Override
	public Product getProduct(String productId) {
		return products.get(productId);
	}
	
	@Override
	public void save(Product product) {
		products.put(product.productId, product);
	}
}
