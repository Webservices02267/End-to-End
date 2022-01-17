Feature: Client create tokens feature

  Scenario: Token status is OK
    When status of token service is called
    Then the service responds with status 200
    And the response text is "Token service ready"
    # If this is the error there might be a race condition on the server not haveing deployed the token service.
    