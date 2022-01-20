package dtu.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PaymentClient extends AbstractClient {


    public Response getStatus() {
        var res = client
                .target(host)
                .path("/services/payment")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return res;
    }

    public static class PaymentDTO {

        public String token;
        public String merchant;
        public String amount;
        public String description;
    }

    public Response pay(int amount, String token, String merchantId, String description) {
        var p = new PaymentDTO();
        p.amount = String.valueOf(amount);
        p.token = token;
        p.description = description;
        p.merchant = merchantId;
        return client.target(host)
                .path("merchants/payments")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(p));
    }
}
