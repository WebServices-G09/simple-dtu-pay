Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull"
    Then it returns the Customer object with name "RedBull"




