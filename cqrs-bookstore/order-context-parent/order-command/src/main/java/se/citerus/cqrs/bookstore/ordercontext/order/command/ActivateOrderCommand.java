package se.citerus.cqrs.bookstore.ordercontext.order.command;

import com.google.common.base.Preconditions;
import se.citerus.cqrs.bookstore.command.Command;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderId;

public class ActivateOrderCommand extends Command {
    public final OrderId orderId;
    public ActivateOrderCommand(OrderId orderId) {
        Preconditions.checkArgument(orderId != null, "OrderId cannot be null");
        this.orderId = orderId;
    }
}
