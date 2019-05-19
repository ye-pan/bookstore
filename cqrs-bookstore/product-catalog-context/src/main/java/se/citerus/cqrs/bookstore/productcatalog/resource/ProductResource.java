package se.citerus.cqrs.bookstore.productcatalog.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.citerus.cqrs.bookstore.productcatalog.api.BookDto;
import se.citerus.cqrs.bookstore.productcatalog.api.ProductDto;
import se.citerus.cqrs.bookstore.productcatalog.api.ProductDtoFactory;
import se.citerus.cqrs.bookstore.productcatalog.domain.Book;
import se.citerus.cqrs.bookstore.productcatalog.domain.Product;
import se.citerus.cqrs.bookstore.productcatalog.domain.ProductRepository;


@RestController
@RequestMapping(path = "products")
public class ProductResource {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ProductRepository productRepository;

	@Autowired
	public ProductResource(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("{productId}")
	public ProductDto getProduct(@PathVariable("productId") String productId) {
		Product product = productRepository.getProduct(productId);
		if(product == null) {
			throw new IllegalArgumentException("No such product: " + productId);
		}
		logger.info("Returning product with id [{}]", product.productId);
		return ProductDtoFactory.fromProduct(product);
	}
	
	@PostMapping
	public void createProduct(@RequestBody @Valid ProductDto request) {
		request.productId = UUID.randomUUID().toString().replace("-", "");
		request.book.bookId = UUID.randomUUID().toString().replace("-", "");
		BookDto bookDto = request.book;
		Book book = new Book(request.book.bookId, bookDto.isbn, bookDto.title, bookDto.description);
		Product product = new Product(request.productId, book, request.price, request.publisherContractId);
		logger.info("Saving product with id [{}]", request.productId);
		productRepository.save(product);
	}
	
	@GetMapping
	public Collection<ProductDto> getProducts() {
		Collection<ProductDto> products = new ArrayList<ProductDto>();
		for (Product product : productRepository.getProducts()) {
			products.add(ProductDtoFactory.fromProduct(product));
		}
		logger.info("Returning [{}] products", products.size());
		return products;
	}
}
