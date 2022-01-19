package dtu.services;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class PaymentServiceSteps {

    PaymentClient client = new PaymentClient();


    /*========================================================================================
    PAYMENT STATUS RESPONSE STEPS
    ========================================================================================*/

    Response paymentStatusResponse;
    @When("status of payment service is called")
    public void statusOfPaymentServiceIsCalled() {
        paymentStatusResponse = client.getStatus();
    }

    @Then("the payment service responds with status {int}")
    public void thePaymentServiceRespondsWithStatus(int arg0) {
        assertEquals(arg0, paymentStatusResponse.getStatus());
    }

    @And("the response from payment service is {string}")
    public void theResponseFromPaymentServiceIs(String arg0) {
        assertEquals(arg0, paymentStatusResponse.readEntity(String.class));
    }
}
