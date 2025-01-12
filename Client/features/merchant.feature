Feature: merchant
  Scenario: Register Merchant
    When a merchant with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    Then a merchant with name "Red" "Bull" has been created

  Scenario: Unregister Merchant
    Given a merchant with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    When the Merchant unregisters
    Then the Merchant is not registred
    And the unkown merchant error message "merchant with id \"%s\" is unknown" is returned