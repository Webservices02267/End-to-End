package dtu.services;

import dtu.services.Entities.Account;
import dtu.services.Entities.CustomerTokensDTO;
import dtu.services.Entities.DTO;
import dtu.services.Entities.ReportDTO;
import dtu.services.Entities.TokenDTO;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    String token;
    String description = "Very good description";
    ReportDTO.Customer customerReport;
    ReportDTO.Merchant merchantReport;

    Response latestResponse;


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
    }


    @Then("the customer have {int} tokens")
    public void theCustomerHaveTokens(Integer numberOfTokens) {
        assertEquals(numberOfTokens.intValue(), tokens.getTokens().size());
    }

    @When("the merchant initiates the payment for {int}")
    public void theMerchantInitiatesThePaymentFor(Integer amount) {
        
        token = tokens.tokens.stream().findFirst().get();
        DTO.CreatePayment payment = new DTO.CreatePayment(token, merchantAccount.accountId, String.valueOf(amount), description);
        System.out.println("Customer Bank account: " + customerBankAccountId + ", Customer Account Id: " + customerAccount.accountId);
        System.out.println("Merchant Bank account: " + merchantBankAccountId + ", Customer Account Id: " + merchantAccount.accountId);
        System.out.println(payment.toString());
        latestResponse = paymentClient.pay(payment);
    }

    @Then("the response code is {int}")
    public void theResponseCodeIs(Integer int1) {
        assertEquals(int1.intValue(), latestResponse.getStatus());
}

    @Then("the balance of the customer is {int}")
    public void theBalanceOfTheCustomerIs(int balance) {
        latestResponse = bankClient.getAccount(customerBankAccountId);
        Account account = latestResponse.readEntity(Account.class);
        assertEquals(new BigDecimal(String.valueOf(balance)), account.getBalance());
    }

    @And("the balance of the merchant is {int}")
    public void theBalanceOfTheMerchantIs(int balance) {
        latestResponse = bankClient.getAccount(merchantBankAccountId);
        Account account = latestResponse.readEntity(Account.class);
        assertEquals(new BigDecimal(String.valueOf(balance)), account.getBalance());
    }


    @When("the customer requests a report")
    public void theCustomerRequestsAReport() throws InterruptedException {
        Thread.sleep(200);
        latestResponse = reportClient.getCustomerReport(customerAccount.accountId);
        System.out.println("Report client statsus: " + latestResponse.getStatus());
        customerReport = latestResponse.readEntity(ReportDTO.Customer.class);
        System.out.println(customerReport);
    }

    @Then("the customer report contains a payment with token of {int}")
    public void theCustomerReportContainsAPaymentWithTokenOf(Integer amount) {
        System.out.println(customerReport + ", " + this.token);
        var expectedPayment =new ReportDTO.CustomerPayment(customerAccount.accountId, merchantAccount.accountId, this.token, String.valueOf(amount));

        assertTrue(customerReport.payments.contains(expectedPayment));
        
        //var actual = customerReport.payments.stream().filter(p -> p.token.equals(this.token)).findFirst().get();
        //System.out.println(actual);
        //assertEquals(this.token, actual);
        //assertTrue(;// && t.amount == String.valueOf(payment)));
    }

    @Then("the report contains a payment")
    public void theReportContainsAPayment() {
        assertTrue(false);
    }

    @When("the merchant requests a report")
    public void theMerchantRequestsAReport() throws InterruptedException {
        Thread.sleep(200);
        latestResponse = reportClient.getMerchantReport(merchantAccount.accountId);
        System.out.println("Report client statsus: " + latestResponse.getStatus());
        merchantReport= latestResponse.readEntity(ReportDTO.Merchant.class);
        System.out.println(merchantReport);
    }

    @Then("the report contains a payment without customerId")
    public void theMerchantReportContainsAPaymentWithoutCustomerId() {
    	System.out.println(merchantReport + ", " + this.token);
        var expectedPayment =new ReportDTO.MerchantPayment(merchantAccount.accountId, this.token, String.valueOf(100));

        assertTrue(merchantReport.payments.contains(expectedPayment));
//        System.out.println(merchantReport + ", " + this.token);
//        var expectedPayment =new ReportDTO.MerchantPayment(merchantAccount.accountId, this.token, String.valueOf(100));
//
//        assertTrue(merchantReport.contains(expectedPayment));
//        assertTrue(false);
    }

    @When("the manager requests a report")
    public void theManagerRequestsAReport() {
        latestResponse = reportClient.getManagerReport();
    }
}
