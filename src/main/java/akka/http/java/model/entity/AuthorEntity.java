package akka.http.java.model.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorEntity {

    private final long id;
    private final String firstName;
    private final String lastName;

    @JsonCreator
    public AuthorEntity(@JsonProperty("id") long id,
                        @JsonProperty("firstName") String firstName,
                        @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
