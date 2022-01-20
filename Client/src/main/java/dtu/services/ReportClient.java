package dtu.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ReportClient extends AbstractClient {

    public Response getStatus() {
        return client
                .target(host)
                .path("/services/report")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response getCustomerReport(String customerId) {
        return client
                .target(host)
                .path("/customers/reports/" + customerId)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response getMerchantReport(String merchantId) {
        return client
                .target(host)
                .path("/merchants/reports/" + merchantId)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
    }

    public Response getManagerReport() {
        return client
                .target(host)
                .path("/managers/reports")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get();
    }
}
