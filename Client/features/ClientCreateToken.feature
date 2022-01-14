Feature: Client create tokens feature

  Scenario: Token status is OK
    When status of token service is called
    Then the service responds with status 200
    And the response text is "OK"
    