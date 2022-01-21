package dtu.services;

import dtu.services.Entities.Account;
import dtu.services.Entities.CustomerTokensDTO;
import dtu.services.Entities.DTO;
import dtu.services.Entities.PaymentDTO;
import dtu.services.Entities.Token;
import dtu.services.Entities.TokenDTO;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompleteSteps {

    AccountClient accountClient = new AccountClient();
    PaymentClient paymentClient = new PaymentClient();
    ReportClient reportClient = new ReportClient();
    TokenClient tokenClient = new TokenClient();
    BankClient bankClient = new BankClient();

    String customerBankAccountId;
    String merchantBankAccountId;
    DTO.CreateAccountResponse customerAccount;
    DTO.CreateAccountResponse merchantAccount;
    TokenDTO tokens;


    @After
    public void retireAccounts() {
        bankClient.deleteAccount(customerBankAccountId);
        bankClient.deleteAccount(merchantBankAccountId);
    }

    @Given("a customer with bank account and balance {int}")
    public void aCustomerWithBankAccountAndBalance(int arg0) {
        var res = bankClient.createAccountAndGetId(arg0, new BankClient.User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        customerBankAccountId = res.readEntity(String.class);
    }

    @Then("the customer have an account number")
    public void theCustomerHaveAnAccountNumber() {
        assertNotNull(customerBankAccountId);
    }

    @And("a merchant with bank account and balance {int}")
    public void aMerchantWithBankAccountAndBalance(int arg0) {
        var res = bankClient.createAccountAndGetId(arg0, new BankClient.User(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        merchantBankAccountId = res.readEntity(String.class);
    }

    @Then("the merchant have an account number")
    public void theMerchantHaveAnAccountNumber() {
        assertNotNull(merchantBankAccountId);
    }


    @And("customer is registered in DTU pay")
    public void customerIsRegisteredInDTUPay() {
        customerAccount = accountClient.registerCustomer(new DTO.CreateAccount(customerBankAccountId)).readEntity(DTO.CreateAccountResponse.class);
        System.out.println("Customer is now signed up with DTY Pay with account: "+customerAccount);
    }

    @And("merchant is registered in DTU pay")
    public void merchantIsRegisteredInDTUPay() {
        merchantAccount = accountClient.registerMerchant(new DTO.CreateAccount(merchantBankAccountId)).readEntity(DTO.CreateAccountResponse.class);
        System.out.println(merchantAccount);
    }

    @When("the customer requests {int} tokens")
    public void theCustomerRequestsTokens(Integer numberOfTokens) {
        
        tokens = tokenClient.createTokens(new CustomerTokensDTO(customerAccount.accountId, numberOfTokens)).readEntity(TokenDTO.class);

        System.out.println(tokens);

        //token = tokens[0];
    }


    @Then("the customer have {int} tokens")
    public void theCustomerHaveTokens(Integer numberOfTokens) {
        assertEquals(numberOfTokens.intValue(), tokens.getTokens().size());
    }

    @When("the merchant initiates the payment for {int}")
    public void theMerchantInitiatesThePaymentFor(int amount) {
        String description = "Very good description";
        PaymentDTO p = new PaymentDTO(tokens.getTokens().get(0), merchantAccount.accountId, amount, description);
        var res = paymentClient.pay(p);
    }

    @Then("the balance of the customer is {int}")
    public void theBalanceOfTheCustomerIs(int arg0) {
        var account = bankClient.getAccount(customerAccount.accountId).readEntity(Account.class);
        assertEquals(account.getBalance(), new BigDecimal(String.valueOf(arg0)));
    }

    @And("the balance of the merchant is {int}")
    public void theBalanceOfTheMerchantIs(int arg0) {
        var account = bankClient.getAccount(merchantAccount.accountId).readEntity(Account.class);
        assertEquals(account.getBalance(), new BigDecimal(String.valueOf(arg0)));
    }


    Response report;
    @When("the customer requests a report")
    public void theCustomerRequestsAReport() {
        report = reportClient.getCustomerReport(customerAccount.accountId);
    }

    @Then("the report contains a payment")
    public void theReportContainsAPayment() {
        assertEquals(200, report.getStatus());
    }

    @When("the merchant requests a report")
    public void theMerchantRequestsAReport() {
        report = reportClient.getMerchantReport(merchantAccount.accountId);
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
