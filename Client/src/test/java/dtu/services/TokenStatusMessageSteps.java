package dtu.services;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

public class TokenStatusMessageSteps {
  TokenClient tokenClient = new TokenClient();
  Response response = null;

  @When("status of token service is called")
  public void statusOfTokenServiceIsCalled() {
    response = tokenClient.getCurrentStatus();

  }

  @Then("the service responds with status {int}")
  public void theServiceRespondsWithStatus(int statusCode) {
    assertEquals(statusCode, response.getStatus());
  }

  @Then("the response text is {string}")
  public void theResponseTextIs(String string) {
    String s = response.readEntity(String.class);
    assertEquals(string, s);
  }

}