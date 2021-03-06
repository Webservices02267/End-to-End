package dtu.services;


import dtu.services.Entities.CustomerTokensDTO;

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


	public Response createTokens(CustomerTokensDTO customerTokenDTO) {
		//String request = new GsonBuilder().setPrettyPrinting().create().toJson(ctd);
		return client.target(host)
				.path("/customers/tokens")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerTokenDTO));
    }
}
