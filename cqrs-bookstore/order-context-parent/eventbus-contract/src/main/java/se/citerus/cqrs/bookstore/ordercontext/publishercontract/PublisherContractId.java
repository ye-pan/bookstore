package se.citerus.cqrs.bookstore.ordercontext.publishercontract;

import se.citerus.cqrs.bookstore.GenericId;

import java.util.UUID;

public class PublisherContractId extends GenericId {
    public PublisherContractId(String id) {
        super(id);
    }

    public static PublisherContractId randomId() {
        return new PublisherContractId(UUID.randomUUID().toString());
    }
}
