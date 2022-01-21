package dtu.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dtu.services.Entities.DTO;
import dtu.services.Entities.PaymentDTO;


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

    public Response pay(DTO.CreatePayment payment) {
        return client.target(host)
                .path("merchants/payments")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(payment));
    }
}
