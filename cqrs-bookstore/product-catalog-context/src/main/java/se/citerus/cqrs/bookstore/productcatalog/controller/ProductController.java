package se.citerus.cqrs.bookstore.productcatalog.controller;

import java.util.ArrayList;
import java.util.Collection;
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
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("{productId}")
	public ProductDto getProduct(@PathVariable("productId") String productId) {
		Product product = productRepository.getOne(productId);
		if(product == null) {
			throw new IllegalArgumentException("No such product: " + productId);
		}
		logger.info("Returning product with id [{}]", product.getId());
		return ProductDtoFactory.fromProduct(product);
	}
	
	@PostMapping
	public void createProduct(@RequestBody @Valid ProductDto request) {
		BookDto bookDto = request.getBook();
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getDescription());
		Product product = new Product(book, request.getPrice(), request.getPublisherContractId());
		productRepository.save(product);
		logger.info("Saving product with id [{}]", product.getId());
	}
	
	@GetMapping
	public Collection<ProductDto> getProducts() {
		Collection<ProductDto> products = new ArrayList<ProductDto>();
		for (Product product : productRepository.findAll()) {
			products.add(ProductDtoFactory.fromProduct(product));
		}
		logger.info("Returning [{}] products", products.size());
		return products;
	}
}
