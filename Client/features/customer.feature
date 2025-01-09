Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull" registers
    Then a Customer with name "RedBull" has been created

  Scenario: get Customer by name
    Given a customer with name "Carla" registers
    When I call the getCustomer service with name "Carla"
    Then I get the Customer with name "Carla"

  Scenario: unregister Customer by Id
    Given an existing customer with Id "UUID1"
    When a customer with Id "UUID1" is unregistered
    Then a customer with Id "UUID1" no longer exists in the customer list
