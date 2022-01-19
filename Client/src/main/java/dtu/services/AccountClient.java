package dtu.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AccountClient extends AbstractClient {
    public Response getStatus() {
        var res = client
                .target(host)
                .path("/customers/status")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return res;
    }
}
