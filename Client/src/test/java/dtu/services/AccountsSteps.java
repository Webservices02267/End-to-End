package dtu.services;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

    @When("customer asks to create account with accountnumber")
    public void customerAsksToCreateAccountWithAccountnumber() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("an account is created")
    public void anAccountIsCreated() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("a customer id is provided")
    public void aCustomerIdIsProvided() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Given("a customer with a profile in payApp")
    public void aCustomerWithAProfileInPayApp() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @When("customer asks to delete account with customer id")
    public void customerAsksToDeleteAccountWithCustomerId() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("the account no longer exists in the account database")
    public void theAccountNoLongerExistsInTheAccountDatabase() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Given("a customer with an account in the bank")
    public void aCustomerWithAnAccountInTheBank() {

    }
}
