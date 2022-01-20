package dtu.services;


import dtu.services.Entities.Token;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TokenClient extends AbstractClient {



	public Response getCurrentStatus() {
		var res = client
				.target(host)
				.path("/services/token")
				.request(MediaType.TEXT_PLAIN)
				.accept(MediaType.TEXT_PLAIN)
				.get();

		return res;
	}

	public static class CustomerTokensDTO {
		public String customerId;
		public int numberOfTokens;
		public CustomerTokensDTO() {

		}
	}

    public Response createTokens(String customerId, int amount) {
		var ctd = new CustomerTokensDTO();
		ctd.customerId = customerId;
		ctd.numberOfTokens = amount;
		return client.target(host)
				.path("/customers/tokens")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(ctd));
    }
}
