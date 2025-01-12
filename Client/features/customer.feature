Feature: customer
  Scenario: Register Customer
    When a customer with name "RedBull" registers
    Then a Customer with name "RedBull" has been created

  Scenario: Unregister Customer
    Given a customer with name "RedBull" registers
    When the Customer unregisters
    Then the Customer is not registred
    And the unkown customer error message "customer with id \"%s\" is unknown" is returned
