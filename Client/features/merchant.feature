Feature: merchant
  Scenario: Register Merchant
    When a merchant with name "Susan" registers
    Then a Merchant with name "Susan" has been created

  Scenario: Unregister Merchant
    Given a merchant with name "RedBull" registers
    When the Merchant unregisters
    Then the Merchant is not registred
    And the unkown merchant error message "merchant with id \"%s\" is unknown" is returned
