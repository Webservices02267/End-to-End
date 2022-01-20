Feature: Complete Run

  Scenario: A vailid customer requests tokens
    Given a customer with bank account and balance 1000
    And customer is registered in DTU pay
    When the customer requests a token
    Then the token service returns tokens

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