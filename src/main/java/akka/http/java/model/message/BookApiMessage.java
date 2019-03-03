package akka.http.java.model.message;

import java.util.Map;


final public class BookApiMessage extends ApiMessage {

    public final String name;
    public final String descr;
    public final long authorId;

    public BookApiMessage(Map<String, String> headers, String name, String descr, long authorId) {
        this.headers = headers;
        this.name = name;
        this.descr = descr;
        this.authorId = authorId;
    }

}
