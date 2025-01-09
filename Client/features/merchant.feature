Feature: merchant
  Scenario: Register Merchant
    When a merchant with name "Susan" registers
    Then a Merchant with name "Susan" has been created

  Scenario: get Customer by name
    Given a merchant with name "Mario" registers
    When I call the getMerchant service with name "Mario"
    Then I get the Merchant with name "Mario"