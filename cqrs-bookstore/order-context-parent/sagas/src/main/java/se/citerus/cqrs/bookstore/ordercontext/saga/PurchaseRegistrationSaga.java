package se.citerus.cqrs.bookstore.ordercontext.saga;

import lombok.extern.slf4j.Slf4j;
import se.citerus.cqrs.bookstore.command.CommandBus;
import se.citerus.cqrs.bookstore.ordercontext.order.event.OrderActivatedEvent;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.command.RegisterPurchaseCommand;
import se.citerus.cqrs.bookstore.ordercontext.query.QueryService;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderLineProjection;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderProjection;
import se.citerus.cqrs.bookstore.saga.Saga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class PurchaseRegistrationSaga extends Saga {
    private final QueryService queryService;
    private final CommandBus commandBus;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public PurchaseRegistrationSaga(QueryService queryService, CommandBus commandBus) {
        this.queryService = queryService;
        this.commandBus = commandBus;
    }

    public void handleEvent(OrderActivatedEvent event) {
        log.info("Received: " + event.toString());
        OrderProjection order = queryService.getOrder(event.aggregateId);
        for (OrderLineProjection orderLine : order.getOrderLines()) {
            PublisherContractId contractId = queryService.findPublisherContract(orderLine.productId);
            publish(new RegisterPurchaseCommand(contractId, orderLine.productId, orderLine.unitPrice, orderLine.quantity));
        }
    }

    private void publish(final RegisterPurchaseCommand command) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                commandBus.dispatch(command);
            }
        });
    }
}
