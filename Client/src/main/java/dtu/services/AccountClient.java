package dtu.services;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AccountClient extends AbstractClient {
    public Response getStatus() {
        var res = client
                .target(host)
                .path("/services/account")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();

        return res;
    }

    public Response registerCustomer(String customerBankAccountId) {
        return client
                .target(host)
                .path("/customers")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(customerBankAccountId));
    }

    public Response registerMerchant(String merchantBankAccountId) {
        return client
                .target(host)
                .path("/merchants")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(merchantBankAccountId));
    }
}
