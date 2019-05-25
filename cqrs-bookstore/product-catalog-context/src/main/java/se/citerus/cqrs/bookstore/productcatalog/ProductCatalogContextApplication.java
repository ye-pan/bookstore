package se.citerus.cqrs.bookstore.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductCatalogContextApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogContextApplication.class, args);
	}

}
