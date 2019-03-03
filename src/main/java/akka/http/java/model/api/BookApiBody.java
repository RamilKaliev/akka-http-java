package akka.http.java.model.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class BookApiBody {

    private final String name;
    private final String descr;
    private final long authorId;

    @JsonCreator
    BookApiBody(@JsonProperty("name") String name,
                @JsonProperty("descr") String descr,
                @JsonProperty("authorId") long authorId) {
        this.name = name;
        this.descr = descr;
        this.authorId = authorId;
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
