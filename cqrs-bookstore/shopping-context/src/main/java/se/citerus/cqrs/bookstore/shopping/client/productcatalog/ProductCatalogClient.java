package se.citerus.cqrs.bookstore.shopping.client.productcatalog;


import org.springframework.web.client.RestTemplate;

public class ProductCatalogClient {
	private final RestTemplate client;
	private final String serviceUrl;
	
	private ProductCatalogClient(RestTemplate client, String serviceUrl) {
		this.client = client;
		this.serviceUrl = serviceUrl;
	}
	
	public static ProductCatalogClient create(RestTemplate client, String serviceUrl) {
		return new ProductCatalogClient(client, serviceUrl);
	}
	
	public ProductDto getProduct(String productId) {
		return client.getForObject(serviceUrl + productId, ProductDto.class);
	}
}
