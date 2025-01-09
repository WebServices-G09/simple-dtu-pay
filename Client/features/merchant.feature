Feature: merchant
  Scenario: Register Merchant
    When a merchant with name "Susan" registers
    Then a Merchant with name "Susan" has been created

  Scenario: get Merchant by name
    Given a merchant with name "Mario" registers
    When I call the getMerchant service with name "Mario"
    Then I get the Merchant with name "Mario"
    
  Scenario: unregister Merchant by Id
    Given an existing merchant with Id "UUID2"
    When a merchant with Id "UUID2" is unregistered
    Then a merchant with Id "UUID2" no longer exists in the merchant list
    