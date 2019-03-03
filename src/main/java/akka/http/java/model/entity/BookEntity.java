package akka.http.java.model.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookEntity {

    private final String id;
    private final String name;
    private final String descr;
    private final long authorId;

    @JsonCreator
    public BookEntity(@JsonProperty("id") String id,
                      @JsonProperty("name") String name,
                      @JsonProperty("descr") String descr,
                      @JsonProperty("authorId") long authorId) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }

    public long getAuthorId() {
        return authorId;
    }

}
