Feature: merchant
  Scenario: Register Merchant
    When a merchant with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    Then a merchant with name "Red" "Bull" has been created