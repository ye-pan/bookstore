package se.citerus.cqrs.bookstore.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.citerus.cqrs.bookstore.TransportObject;

@Data
@AllArgsConstructor
public class ApiMessageDto extends TransportObject  {
    private String message;
}
