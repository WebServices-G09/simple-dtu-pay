Feature: customer
  Scenario: Register Customer
    When a customer with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    Then a Customer with name "Red" "Bull" has been created

  Scenario: Unregister Customer
    Given a customer with fname "Red" lName "Bull" cpr "1123089123" accountNum "21083091sd" registers
    When the Customer unregisters
    Then the Customer is not registred
    And the unkown customer error message "customer with id \"%s\" is unknown" is returned