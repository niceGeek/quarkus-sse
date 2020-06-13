package boundary;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.core.Vertx;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Path("/stream")
public class StreamingResource {

    @Inject
    Vertx vertx;

    private static final String WEATHER_API =
            "https://api.openweathermap.org/data/2.5/weather?q=Nuremberg,de&APPID=14a76b26d284a9dafba3bfb1bf36cf30";


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/{name}")
    public Multi<String> greeting(@PathParam("name") String name) {
        return vertx.periodicStream(1000).toMulti()
                .map(l -> String.format("Hello %s (%s)%n", name, jsonGetRequest(WEATHER_API)));
    }

    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }
}
