package se.citerus.cqrs.bookstore.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShoppingContextApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingContextApplication.class, args);
	}

}
