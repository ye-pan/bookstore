package se.citerus.cqrs.bookstore.ordercontext.client.productcatalog;

import org.springframework.web.client.RestTemplate;

public class ProductCatalogClient {
    private RestTemplate restTemplate;
    private final String serviceUrl;
    private ProductCatalogClient(RestTemplate restTemplate, String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public static ProductCatalogClient create(RestTemplate restTemplate, String serviceUrl) {
        return new ProductCatalogClient(restTemplate, serviceUrl);
    }

    public ProductDto getProduct(String productId) {
        return restTemplate.getForObject(serviceUrl + productId, ProductDto.class);
    }
}
