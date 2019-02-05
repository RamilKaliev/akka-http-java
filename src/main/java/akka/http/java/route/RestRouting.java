package akka.http.java.route;


import akka.http.java.model.api.AuthorApiBody;
import akka.http.java.model.entity.AuthorEntity;
import akka.http.java.model.message.AuthorApiMessage;
import akka.http.java.service.RestHandlerService;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.marshallers.jackson.Jackson;

import java.util.Optional;
import java.util.concurrent.CompletionStage;


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
                        path("authors", () ->
                                post(() ->
                                        entity(Jackson.unmarshaller(AuthorApiBody.class), body -> {
                                                CompletionStage<Optional<AuthorEntity>> persist = restHandlerService
                                                        .saveAuthor(new AuthorApiMessage(null, body.getFirstName(), body.getLastName()));
                                                return onSuccess(persist, entityOpt ->
                                                    entityOpt.map(entity -> completeOK(entity, Jackson.marshaller())).orElseGet(null)
                                                );
                                            }
                                        )
                                )
                        )
                )
        ));

    }

}
