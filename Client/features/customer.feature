Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull"
    Then it returns the Customer object with name "RedBull"

  Scenario: Get Customer by Id
    Given a customer Id
    When there is a record with that Id
    Then it returns the Customer object with Id "id"


