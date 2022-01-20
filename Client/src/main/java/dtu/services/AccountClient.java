package dtu.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dtu.services.Entities.DTO;

public class AccountClient extends AbstractClient {
    public Response getStatus() {
        return client
                .target(host)
                .path("/services/account")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response registerCustomer(DTO.CreateAccount customerBankAccountId) {
        return client
                .target(host)
                .path("/customers")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(customerBankAccountId));
    }

    public Response registerMerchant(DTO.CreateAccount merchantBankAccountId) {
        return client
                .target(host)
                .path("/merchants")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(merchantBankAccountId));
    }
}
