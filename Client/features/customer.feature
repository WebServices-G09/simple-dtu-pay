Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull" registers
    Then a Customer with name "RedBull" has been created
