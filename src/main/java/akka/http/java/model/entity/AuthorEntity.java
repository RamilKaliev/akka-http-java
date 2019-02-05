package akka.http.java.model.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorEntity {

    private long id;
    private String firstName;
    private String lastName;

    @JsonCreator
    public AuthorEntity(@JsonProperty("id") long id,
                        @JsonProperty("firstName") String firstName,
                        @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
