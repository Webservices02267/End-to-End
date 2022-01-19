package dtu.services;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
}
