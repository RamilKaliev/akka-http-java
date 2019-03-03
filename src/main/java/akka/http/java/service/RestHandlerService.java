package akka.http.java.service;

import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.entity.BookEntity;
import akka.http.java.model.message.AuthorApiMessage;
import akka.http.java.model.message.BookApiMessage;
import akka.http.java.repository.InMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RestHandlerService {

    final private InMemoryRepository repository;


    public RestHandlerService(InMemoryRepository repository) {
        this.repository = repository;
    }

    /*
    AUTHOR
     */
    // SAVE
    public CompletionStage<AuthorEntity> saveAuthor(AuthorApiMessage message) {
        return CompletableFuture.completedFuture(
                repository.addAuthor(message.firstName, message.lastName)
        );
    }

    // READ
    public CompletionStage<List<AuthorEntity>> getAuthors() {
        return CompletableFuture.completedFuture(
                repository.getAuthors()
        );
    }


    /*
    BOOK
     */
    // SAVE
    public CompletionStage<BookEntity> saveBook(BookApiMessage message) {
        return CompletableFuture.completedFuture(
                repository.addBook(message.name, message.descr, message.authorId)
        );
    }


    // READ
    public CompletionStage<List<BookEntity>> getBooks() {
        return CompletableFuture.completedFuture(
                repository.getBooks()
        );
    }


}
