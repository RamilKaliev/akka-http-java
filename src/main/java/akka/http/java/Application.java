package akka.http.java;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.java.repository.H2Repository;
import akka.http.java.route.RestRouting;
import akka.http.java.service.RestHandlerService;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.BasicConfigurator;

public class Application {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        startUp();
    }



    private static void startUp() {

        Config config = ConfigFactory.load();

        // akka core
        ActorSystem system = ActorSystem.create("akka-http-system");
        ActorMaterializer materializer = ActorMaterializer.create(system);

        // akka http
        Http http = Http.get(system);


        // h2 database repository
        H2Repository repository = new H2Repository("jdbc:h2:bookstore");
        if (repository.check()) {
            repository.init();
        } else {
            System.exit(0);
        }

        // http rest handler
        RestHandlerService restHandler = new RestHandlerService(repository);

        // http rest rounting
        RestRouting restRouting = new RestRouting("v1", "bookstore", restHandler);

        final Flow<HttpRequest, HttpResponse, NotUsed> flow = restRouting.createRoutes().flow(system, materializer);

        http.bindAndHandle(flow, ConnectHttp.toHost(config.getString("server.host"), config.getInt("server.port")), materializer);
        System.out.println("Server online at http://localhost:8080/...");
    }


}
