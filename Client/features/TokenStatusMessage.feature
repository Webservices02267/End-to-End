Feature: Token status message connection

  Scenario: Token status is OK
    When status of token service is called
    Then the service responds with status 200
    And the response text is "Token service ready"
    #TODO: Token service ready is not the right string.?
    # If this is the error there might be a race condition on the server not haveing deployed the token service.
    