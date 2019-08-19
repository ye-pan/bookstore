package se.citerus.cqrs.bookstore.shopping.client.productcatalog;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ProductCatalogClient {

	private final RestTemplate client;

	@Value("${service.product.uri}")
	private String uri;

	public ProductCatalogClient(RestTemplate client) {
		this.client = client;
	}
	
	public ProductDto getProduct(String productId) {
		String url = uri + "/{productId}";
		log.debug("url:{} parameter:{}", url, productId);
		return client.getForObject(url, ProductDto.class, productId);
	}
}
