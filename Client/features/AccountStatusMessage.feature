Feature: Token status message connection

  Scenario: Account status is OK
    When status of account service is called
    Then the account service responds with status 200
    And the response from account service is "Sanitity check for account service"
