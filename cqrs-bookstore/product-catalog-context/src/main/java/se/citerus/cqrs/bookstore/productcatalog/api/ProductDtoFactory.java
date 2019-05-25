package se.citerus.cqrs.bookstore.productcatalog.api;

import se.citerus.cqrs.bookstore.productcatalog.domain.Book;
import se.citerus.cqrs.bookstore.productcatalog.domain.Product;

public class ProductDtoFactory {
	
	public static ProductDto fromProduct(Product product) {
		return toProduct(product, toBook(product.getBook()));
	}
	
	private static ProductDto toProduct(Product product, BookDto book) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setPrice(product.getPrice());
		productDto.setPublisherContractId(product.getPublisherContractId());
		productDto.setBook(book);
		return productDto;
	}
	
	private static BookDto toBook(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setBookId(book.getBookId());
		bookDto.setIsbn(book.getIsbn());
		bookDto.setTitle(book.getTitle());
		bookDto.setDescription(book.getDescription());
		return bookDto;
	}
}
