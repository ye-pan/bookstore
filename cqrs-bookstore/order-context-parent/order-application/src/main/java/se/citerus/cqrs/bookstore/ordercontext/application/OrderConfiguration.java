package se.citerus.cqrs.bookstore.ordercontext.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import se.citerus.cqrs.bookstore.command.CommandBus;
import se.citerus.cqrs.bookstore.event.DomainEventBus;
import se.citerus.cqrs.bookstore.ordercontext.application.infrastructure.DefaultRepository;
import se.citerus.cqrs.bookstore.ordercontext.application.infrastructure.GuavaCommandBus;
import se.citerus.cqrs.bookstore.ordercontext.client.productcatalog.ProductCatalogClient;
import se.citerus.cqrs.bookstore.ordercontext.order.command.OrderCommandHandle;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.command.PublisherContractCommandHandler;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderListDenormalizer;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderProjectionRepository;
import se.citerus.cqrs.bookstore.ordercontext.query.sales.OrdersPerDayAggregator;

@Configuration
public class OrderConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ProductCatalogClient catalogClient(RestTemplate restTemplate) {
        return ProductCatalogClient.create(restTemplate, "http://localhost:9998/products/");
    }

    @Bean
    public OrderListDenormalizer orderListDenormalizer(DomainEventBus domainEventBus, OrderProjectionRepository orderProjectionRepository) {
        return domainEventBus.register(new OrderListDenormalizer(orderProjectionRepository));
    }

    @Bean
    public OrdersPerDayAggregator ordersPerDayAggregator(DomainEventBus domainEventBus) {
        return domainEventBus.register(new OrdersPerDayAggregator());
    }

    @Bean
    public CommandBus commandBus(DefaultRepository aggregateRepository) {
        CommandBus commandBus = GuavaCommandBus.syncGuavaCommandBus();
        commandBus.register(new OrderCommandHandle(aggregateRepository));
        commandBus.register(new PublisherContractCommandHandler(aggregateRepository));
        return commandBus;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
