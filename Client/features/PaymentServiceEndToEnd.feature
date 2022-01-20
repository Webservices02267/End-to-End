Feature: Payment Service
  Scenario: Payment responds with ok to status
    When status of payment service is called
    Then the payment service responds with status 200
    And the response from payment service is "Sanity check for payment service"