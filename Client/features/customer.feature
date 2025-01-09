Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull" registers
    Then a Customer with name "RedBull" has been created

  Scenario: get Customer by name
    Given a customer with name "Carla" registers
    When I call the getCustomer service with name "Carla"
    Then I get the Customer with name "Carla"






