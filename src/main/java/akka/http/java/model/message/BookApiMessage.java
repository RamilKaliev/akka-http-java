package akka.http.java.model.message;

import java.util.Map;


final public class BookApiMessage extends ApiMessage {

    final String name;
    final String descr;
    final long authorId;

    BookApiMessage(Map<String, String> headers, String name, String descr, long authorId) {
        this.headers = headers;
        this.name = name;
        this.descr = descr;
        this.authorId = authorId;
    }

}
