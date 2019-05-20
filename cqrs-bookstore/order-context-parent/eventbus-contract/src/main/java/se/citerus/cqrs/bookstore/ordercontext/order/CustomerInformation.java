package se.citerus.cqrs.bookstore.ordercontext.order;

import se.citerus.cqrs.bookstore.TransportObject;

public class CustomerInformation extends TransportObject {
    public final String customerName;
    public final String email;
    public final String address;

    public CustomerInformation(String customerName, String email, String address) {
        this.customerName = customerName;
        this.email = email;
        this.address = address;
    }
}
