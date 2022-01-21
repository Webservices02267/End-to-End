Feature: Complete Run

    Scenario: A merchant registers with the bank
    Given a merchant with bank account and balance 1000
    And merchant is registered in DTU pay
    Then the merchant have an account number

  Scenario: A customer registers with the bank
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    Then the customer have an account number

  Scenario: A vailid customer requests tokens
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    When the customer requests 5 tokens
    Then the customer have 5 tokens

  Scenario: A merchant does a payment
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    And a merchant with bank account and balance 1000
    And merchant is registered in DTU pay
    When the customer requests 5 tokens
    Then the customer have 5 tokens
    When the merchant initiates the payment for 100
    Then the response code is 200
    Then the balance of the customer is 900
    And the balance of the merchant is 1100


  Scenario: Client requests report of payment
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    And a merchant with bank account and balance 1000
    And merchant is registered in DTU pay
    When the customer requests 5 tokens
    Then the customer have 5 tokens
    When the merchant initiates the payment for 100
    Then the response code is 200
    Then the balance of the customer is 900
    And the balance of the merchant is 1100
    When the customer requests a report
    Then the response code is 200
    Then the customer report contains a payment with token of 100
    
    
   Scenario: Merchant requests report of payment
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    And a merchant with bank account and balance 1000
    And merchant is registered in DTU pay
    When the customer requests 5 tokens
    Then the customer have 5 tokens
    When the merchant initiates the payment for 100
    Then the response code is 200
    Then the balance of the customer is 900
    And the balance of the merchant is 1100
    When the merchant requests a report
    Then the response code is 200
    Then the report contains a payment without customerId


#  Scenario: Complete run
#    Given a customer with bank account and balance 1000
#    And a merchant with bank account and balance 1000
#    And customer is registered in DTU pay
#    And merchant is registered in DTU pay
#    When the customer requests a token
#    Then the token service returns tokens
#    When the merchant initiates the payment for 100
#    Then the balance of the customer is 900
#    And the balance of the merchant is 1100
#    When the customer requests a report
#    Then the report contains a payment
#    When the merchant requests a report
#    Then the report contains a payment without customerId
#    When the manager requests a report
#    Then the report contains a payment