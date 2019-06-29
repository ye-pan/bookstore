package se.citerus.cqrs.bookstore.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProductCatalogContextApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.main(ProductCatalogContextApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogContextApplication.class, args);
	}

}
