Feature: account service end to end test

  Scenario: Customer wants to create profile
    Given a customer with an account in the bank
    When customer asks to create account with accountnumber
    Then an account is created, and a customer id is provided

  Scenario: Customer wants to delete profile
    Given a customer with a profile in payApp
    When customer asks to delete account with customer id
    Then the account is removed from the account database