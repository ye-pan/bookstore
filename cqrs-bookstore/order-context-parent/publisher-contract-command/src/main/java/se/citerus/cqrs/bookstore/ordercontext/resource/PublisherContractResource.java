package se.citerus.cqrs.bookstore.ordercontext.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.citerus.cqrs.bookstore.api.ApiMessageDto;
import se.citerus.cqrs.bookstore.command.CommandBus;
import se.citerus.cqrs.bookstore.ordercontext.api.RegisterPublisherContractRequest;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.command.CommandFactory;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.command.RegisterPublisherContractCommand;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("publisher-contract-requests")
public class PublisherContractResource {
    private final CommandBus commandBus;
    private final CommandFactory commandFactory = new CommandFactory();

    public PublisherContractResource(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    public ApiMessageDto registerContract(@RequestBody @Valid RegisterPublisherContractRequest request) {
        PublisherContractId publisherContractId = new PublisherContractId(request.publisherContractId);
        log.info("Registering contract: " + publisherContractId);
        RegisterPublisherContractCommand command = commandFactory.toCommand(publisherContractId, request);
        commandBus.dispatch(command);
        return new ApiMessageDto("操作成功!");
    }
}
