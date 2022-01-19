package dtu.services;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class AccountsSteps {
    AccountClient client = new AccountClient();


    /*========================================================================================
    ACCOUNT STATUS RESPONSE STEPS
    ========================================================================================*/

    Response accountStatusResponse;
    @When("status of account service is called")
    public void statusOfAccountServiceIsCalled() {
        accountStatusResponse = client.getStatus();
    }

    @Then("the account service responds with status {int}")
    public void theAccountServiceRespondsWithStatus(int arg0) {
        assertEquals(arg0, accountStatusResponse.getStatus());
    }

    @And("the response from account service is {string}")
    public void theResponseFromAccountServiceIs(String arg0) {
        assertEquals(arg0, accountStatusResponse.readEntity(String.class));
    }
}
