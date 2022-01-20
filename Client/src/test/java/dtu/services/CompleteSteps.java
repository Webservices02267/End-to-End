package dtu.services;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.client.Entity;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class CompleteSteps {

    AccountClient accountClient = new AccountClient();
    PaymentClient paymentClient = new PaymentClient();
    ReportClient reportClient = new ReportClient();
    TokenClient tokenClient = new TokenClient();
    BankClient bankClient = new BankClient();

    @After
    public void retireAccounts() {

    }

    @Given("a customer with bank account and balance {int}")
    public void aCustomerWithBankAccountAndBalance(int arg0) {
        var res = bankClient.createAccountAndGetId(arg0, new BankClient.User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        var msg = res.readEntity(String.class);
        System.out.println(msg);
        res = bankClient.deleteAccount(msg);
        System.out.println(res.getStatus());
        System.out.println(Arrays.toString(bankClient.getAccounts()));

    }

    @And("a merchant with bank account and balance {int}")
    public void aMerchantWithBankAccountAndBalance(int arg0) {
    }

    @And("customer is registered in DTU pay")
    public void customerIsRegisteredInDTUPay() {
    }

    @And("merchant is registered in DTU pay")
    public void merchantIsRegisteredInDTUPay() {
    }

    @When("the customer requests a token")
    public void theCustomerRequestsAToken() {
    }

    @Then("the token service returns tokens")
    public void theTokenServiceReturnsTokens() {
    }

    @When("the merchant initiates the payment for {int}")
    public void theMerchantInitiatesThePaymentFor(int arg0) {
    }

    @Then("the balance of the customer is {int}")
    public void theBalanceOfTheCustomerIs(int arg0) {
    }

    @And("the balance of the merchant is {int}")
    public void theBalanceOfTheMerchantIs(int arg0) {
    }

    @When("the customer requests a report")
    public void theCustomerRequestsAReport() {
    }

    @Then("the report contains a payment")
    public void theReportContainsAPayment() {
    }

    @When("the merchant requests a report")
    public void theMerchantRequestsAReport() {
    }

    @Then("the report contains a payment without customerId")
    public void theReportContainsAPaymentWithoutCustomerId() {
    }

    @When("the manager requests a report")
    public void theManagerRequestsAReport() {
    }
}
