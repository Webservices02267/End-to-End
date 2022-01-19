Feature: Token status message connection

  Scenario: Account status is OK
    When status of account service is called
    Then the service responds with status 200
    And the response text is "Sanitity check for account service"
    # If this is the error there might be a race condition on the server not haveing deployed the token service.
