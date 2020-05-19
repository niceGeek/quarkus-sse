package boundary;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.core.Vertx;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/stream")
public class StreamingResource {

    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/{name}")
    public Multi<String> greeting(@PathParam String name) {
        return vertx.periodicStream(2000).toMulti()
                .map(l -> String.format("Hello %s! (%s)%n", name, new Date()));
    }
}
