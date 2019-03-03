package akka.http.java.repository;

import akka.http.java.cache.InMemoryCache;
import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.entity.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;


public class InMemoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryRepository.class);

    InMemoryCache cache = null;

    public InMemoryRepository(InMemoryCache cache) {
        this.cache = cache;
    }


    /*
    AUTHORS
     */
    public AuthorEntity addAuthor(String firstName, String lastName) {
        return cache.addAuthor(new AuthorEntity((long)Math.random() * 100, firstName, lastName));
    }

    public List<AuthorEntity> getAuthors() {
        return cache.getAuthors();
    }


    /*
    BOOKS
     */
    public BookEntity addBook(String name, String descr, long authorId) {
        return cache.addBook(new BookEntity(UUID.randomUUID().toString(), name, descr, authorId));
    }

    public List<BookEntity> getBooks() {
        return cache.getBooks();
    }

}
