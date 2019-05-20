package se.citerus.cqrs.bookstore.ordercontext.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.citerus.cqrs.bookstore.api.ApiMessageDto;
import se.citerus.cqrs.bookstore.command.CommandBus;
import se.citerus.cqrs.bookstore.ordercontext.api.ActivateOrderRequest;
import se.citerus.cqrs.bookstore.ordercontext.api.PlaceOrderRequest;
import se.citerus.cqrs.bookstore.ordercontext.order.command.ActivateOrderCommand;
import se.citerus.cqrs.bookstore.ordercontext.order.command.CommandFactory;
import se.citerus.cqrs.bookstore.ordercontext.order.command.PlaceOrderCommand;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("order-requests")
public class OrderResource {
    private final CommandBus commandBus;
    private final CommandFactory commandFactory = new CommandFactory();

    public OrderResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    public ApiMessageDto placeOrder(@RequestBody @Valid PlaceOrderRequest placeOrderRequest) {
        log.info("Placing customer order: " + placeOrderRequest);
        PlaceOrderCommand placeOrderCommand = commandFactory.toCommand(placeOrderRequest);
        commandBus.dispatch(placeOrderCommand);
        return new ApiMessageDto("操作成功!");
    }

    @PostMapping("/activations")
    public ApiMessageDto activateOrder(@RequestBody @Valid ActivateOrderRequest activationRequest) {
        log.info("Activating orderId: " + activationRequest);
        ActivateOrderCommand command = commandFactory.toCommand(activationRequest);
        commandBus.dispatch(command);
        return new ApiMessageDto("操作成功!");
    }
}
