package akka.http.java.route;


import akka.http.java.model.api.AuthorApiBody;
import akka.http.java.model.api.BookApiBody;
import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.entity.BookEntity;
import akka.http.java.model.message.AuthorApiMessage;
import akka.http.java.model.message.BookApiMessage;
import akka.http.java.service.RestHandlerService;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.marshallers.jackson.Jackson;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;


/**
 * RestRounting - router for http request which can be handled by this service
 *
 * NOTES: In my opinion, processing should be organized by Response rather than OnSuccess statement.
 *
 */
public class RestRouting extends AllDirectives {

    String version;
    String context;
    RestHandlerService restHandlerService;

    public RestRouting(String version, String context, RestHandlerService restHandlerService) {
        this.version = version;
        this.context = context;
        this.restHandlerService = restHandlerService;
    }


    public Route createRoutes() {

        return pathPrefix(version, () -> pathPrefix(PathMatchers.separateOnSlashes(context), () ->
                concat(
                        path("healthcheck", () ->
                                get(() -> complete("OK"))
                        ),
                        path("authors", () -> concat(
                                post(() -> entity(Jackson.unmarshaller(AuthorApiBody.class), body -> {
                                    CompletionStage<AuthorEntity> stage = restHandlerService.saveAuthor(
                                            new AuthorApiMessage(null, body.getFirstName(), body.getLastName())
                                    );
                                    return onSuccess(stage, entity -> completeOK(entity, Jackson.marshaller()));

                                })),
                                get(() -> {
                                    CompletionStage<List<AuthorEntity>> authors = restHandlerService.getAuthors();
                                    return completeOKWithFuture(authors, Jackson.marshaller());
                                })
                            )
                        ),
                        path("books", () -> concat(
                                post(() -> entity(Jackson.unmarshaller(BookApiBody.class), body -> {
                                    CompletionStage<BookEntity> stage = restHandlerService.saveBook(
                                            new BookApiMessage(null, body.getName(), body.getDescr(), body.getAuthorId())
                                    );
                                    return onSuccess(stage, entity -> completeOK(entity, Jackson.marshaller()));
                                })),
                                get(() -> {
                                    CompletionStage<List<BookEntity>> books = restHandlerService.getBooks();
                                    return completeOKWithFuture(books, Jackson.marshaller());
                                })
                        ))
                )
        ));

    }

}
