package akka.http.java.model.message;

import java.util.Map;


final public class AuthorApiMessage extends ApiMessage {

    final public String firstName;
    final public String lastName;

    public AuthorApiMessage(Map<String, String> headers, String firstName, String lastName) {
        this.headers = headers;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
