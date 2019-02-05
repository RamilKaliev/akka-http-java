package akka.http.java.model.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorApiBody {

    private final String firstName;
    private final String lastName;

    @JsonCreator
    AuthorApiBody(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
