Feature: customer
  Scenario: Register Customer
    When a customer with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    Then a Customer with name "Red" "Bull" has been created





