package akka.http.java.model.entity;



public class BookEntity {

    private String id;
    private String name;
    private String descr;
    private long authorId;


    BookEntity(String id, String name, String descr, long authorId) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.authorId = authorId;
    }

}
