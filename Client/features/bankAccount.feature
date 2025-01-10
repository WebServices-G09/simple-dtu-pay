Feature: Create and verify a bank account

  Scenario: Creating a bank account with user details
    When a user with firstName "fdsfd" lastName "sadad" cpr "123456-1843" and initial balance 1000.0 creates an account
    Then an account with firstName "fdsfd" lastName "sadad" and balance 1000.0 should be created
