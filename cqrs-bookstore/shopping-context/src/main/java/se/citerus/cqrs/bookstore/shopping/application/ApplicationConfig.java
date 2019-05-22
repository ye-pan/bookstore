package se.citerus.cqrs.bookstore.shopping.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import se.citerus.cqrs.bookstore.shopping.client.productcatalog.ProductCatalogClient;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ProductCatalogClient productCatalogClient(RestTemplate restTemplate) {
		return ProductCatalogClient.create(restTemplate, "http://localhost:9998/products/");
	}
}
