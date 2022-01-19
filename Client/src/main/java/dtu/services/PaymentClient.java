package dtu.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PaymentClient {
    public static final String HTTP_LOCALHOST_8080 = "http://localhost:8080";
    public static final String HTTP_DOCKER_LOCALHOST_8181 = "http://e2e-rest:8181";
    public static String host = HTTP_LOCALHOST_8080;
    private final Client client = ClientBuilder.newClient();

    public PaymentClient(){
        // For CI in jenkins, we need to provide a Docker specific host:port combination
        if ("True".equals(System.getenv("IN_DOCKER_ENV"))) {
            host = HTTP_DOCKER_LOCALHOST_8181;
            System.out.println("Running in a Dockerfile has been detected. Changed the host and port.");
        }
    }

    public Response getStatus() {
        var res = client
                .target(host)
                .path("/payments/status")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return res;
    }
}
