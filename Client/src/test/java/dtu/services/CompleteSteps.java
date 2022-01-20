package dtu.services;

import dtu.services.Entities.Account;
import dtu.services.Entities.AccountDTO;
import dtu.services.Entities.Token;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CompleteSteps {

    AccountClient accountClient = new AccountClient();
    PaymentClient paymentClient = new PaymentClient();
    ReportClient reportClient = new ReportClient();
    TokenClient tokenClient = new TokenClient();
    BankClient bankClient = new BankClient();

    String customerBankAccountId;
    String merchantBankAccountId;
    AccountDTO customerId;
    String merchantId;
    String token;



    @After
    public void retireAccounts() {
        bankClient.deleteAccount(customerBankAccountId);
        bankClient.deleteAccount(merchantBankAccountId);
    }

    @Given("a customer with bank account and balance {int}")
    public void aCustomerWithBankAccountAndBalance(int arg0) {
        var res = bankClient.createAccountAndGetId(arg0, new BankClient.User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        customerBankAccountId = res.readEntity(String.class);
        System.out.println(customerBankAccountId);
    }

    @And("a merchant with bank account and balance {int}")
    public void aMerchantWithBankAccountAndBalance(int arg0) {
        var res = bankClient.createAccountAndGetId(arg0, new BankClient.User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        merchantBankAccountId = res.readEntity(String.class);
        System.out.println(merchantBankAccountId);
    }


    @And("customer is registered in DTU pay")
    public void customerIsRegisteredInDTUPay() {
        customerId = accountClient.registerCustomer(new AccountDTO(customerBankAccountId)).readEntity(AccountDTO.class);
        System.out.println(customerId);
    }

    @And("merchant is registered in DTU pay")
    public void merchantIsRegisteredInDTUPay() {
        merchantId = accountClient.registerMerchant(merchantBankAccountId).readEntity(String.class);
        System.out.println(merchantId);
    }

    @When("the customer requests a token")
    public void theCustomerRequestsAToken() {
        var tokens = tokenClient.createTokens(customerId.getAccountNumber(), 5).readEntity(String.class);
        System.out.println(tokens);
        //token = tokens[0];
    }

    @Then("the token service returns tokens")
    public void theTokenServiceReturnsTokens() {

    }

    @When("the merchant initiates the payment for {int}")
    public void theMerchantInitiatesThePaymentFor(int amount) {
        String description = "Very good description";
        var res = paymentClient.pay(amount, token, merchantId, description);
    }

    @Then("the balance of the customer is {int}")
    public void theBalanceOfTheCustomerIs(int arg0) {
        var account = bankClient.getAccount(customerId.getAccountNumber()).readEntity(Account.class);
        assertEquals(account.getBalance(), new BigDecimal(String.valueOf(arg0)));
    }

    @And("the balance of the merchant is {int}")
    public void theBalanceOfTheMerchantIs(int arg0) {
        var account = bankClient.getAccount(merchantId).readEntity(Account.class);
        assertEquals(account.getBalance(), new BigDecimal(String.valueOf(arg0)));
    }


    Response report;
    @When("the customer requests a report")
    public void theCustomerRequestsAReport() {
        report = reportClient.getCustomerReport(customerId.getAccountNumber());
    }

    @Then("the report contains a payment")
    public void theReportContainsAPayment() {
        assertEquals(200, report.getStatus());
    }

    @When("the merchant requests a report")
    public void theMerchantRequestsAReport() {
        report = reportClient.getMerchantReport(merchantId);
    }

    @Then("the report contains a payment without customerId")
    public void theReportContainsAPaymentWithoutCustomerId() {
        assertEquals(200, report.getStatus());
    }

    @When("the manager requests a report")
    public void theManagerRequestsAReport() {
        report = reportClient.getManagerReport();
    }
}
