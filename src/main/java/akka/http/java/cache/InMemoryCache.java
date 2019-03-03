package akka.http.java.cache;


import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.entity.BookEntity;

import java.util.*;

/*
In-memory storage
 */
public class InMemoryCache {

    private final static HashMap<String, AuthorEntity> AUTHORS = new HashMap<>(5);
    private final static HashMap<String, BookEntity> BOOKS = new HashMap<>(5);


    static {
        // insert initial authors
        long authorId = (long)Math.random() * 100;
        AUTHORS.put(UUID.randomUUID().toString(), new AuthorEntity(authorId + 1, "Charles", "Dickens"));
        AUTHORS.put(UUID.randomUUID().toString(), new AuthorEntity(authorId + 2, "Arthur", "Konan-Doyle"));
        AUTHORS.put(UUID.randomUUID().toString(), new AuthorEntity(authorId + 3, "Alex", "Pushkin"));
        AUTHORS.put(UUID.randomUUID().toString(), new AuthorEntity(authorId + 4, "Michail", "Lermontov"));
        AUTHORS.put(UUID.randomUUID().toString(), new AuthorEntity(authorId + 5, "Joseph", "Cronin"));

        //
        BOOKS.put(
                UUID.randomUUID().toString(),
                new BookEntity(UUID.randomUUID().toString(), "The great expectations", "(unspecified)", authorId + 1)
        );
        BOOKS.put(
                UUID.randomUUID().toString(),
                new BookEntity(UUID.randomUUID().toString(), "The sign of Four", "(unspecified)", authorId + 2)
        );
        BOOKS.put(
                UUID.randomUUID().toString(),
                new BookEntity(UUID.randomUUID().toString(), "Some poetry", "(unspecified)", authorId + 3)
        );
        BOOKS.put(
                UUID.randomUUID().toString(),
                new BookEntity(UUID.randomUUID().toString(), "Borodino", "(unspecified)", authorId + 4)
        );
        BOOKS.put(
                UUID.randomUUID().toString(),
                new BookEntity(UUID.randomUUID().toString(), "The Citadel", "(unspecified)", authorId + 5)
        );
    }


    /*
    AUTHORS
     */
    public List<AuthorEntity> getAuthors() {
        return Collections.list(Collections.enumeration(AUTHORS.values()));
    }

    public AuthorEntity addAuthor(AuthorEntity entity) {
        AUTHORS.put(UUID.randomUUID().toString(), entity);
        return entity;
    }


    /*
    BOOKS
     */
    public List<BookEntity> getBooks() {
        return Collections.list(Collections.enumeration(BOOKS.values()));
    }

    public BookEntity addBook(BookEntity entity) {
        BOOKS.put(UUID.randomUUID().toString(), entity);
        return entity;
    }

}
