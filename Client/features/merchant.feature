Feature: merchant
  Scenario: Register Merchant
    When a merchant with name "Susan" registers
    Then a Merchant with name "Susan" has been created
    