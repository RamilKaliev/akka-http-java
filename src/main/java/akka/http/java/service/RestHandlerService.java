package akka.http.java.service;

import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.message.AuthorApiMessage;
import akka.http.java.repository.H2Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RestHandlerService {

    final private H2Repository h2Repository;


    public RestHandlerService(H2Repository h2Repository) {
        this.h2Repository = h2Repository;
    }


    // SAVE AUTHOR future
    public CompletionStage<Optional<AuthorEntity>> saveAuthor(AuthorApiMessage message) {
        return CompletableFuture.completedFuture(
                Optional.of(h2Repository.saveAuthor(message.firstName, message.lastName))
        );
    }


    public CompletionStage<List<AuthorEntity>> getAuthors() {
        return CompletableFuture.completedFuture(
                null
        );
    }


}
