package boundary;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("lorem")
public class GreetingResource {

    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> doSomethingAsync() {
        // Mimic an asynchronous computation
        /*
        return Uni.createFrom()
                .item(() -> "Hello")
                .onItem().delayIt().by(Duration.ofMillis(10));
        */

        return vertx.fileSystem().readFile("/META-INF/resources/lorem.txt")
                .onItem().apply(b -> b.toString("UTF-8"));
    }
}
