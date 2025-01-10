Feature: Bank Account Management

  Scenario: Creating a bank account with user details
    When a user with firstName "Alice" lastName "Smith" cpr "987654-3210" and initial balance 5000.0 creates an account
    Then an account with firstName "Alice" lastName "Smith" and balance 5000.0 should be created