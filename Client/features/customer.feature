Feature: customer
  Scenario: Register Customer
    When a customer with fname "Red" lName "Bull" cpr 1111111111 accountNum 1 registers
    Then a Customer with name "RedBull" has been created





