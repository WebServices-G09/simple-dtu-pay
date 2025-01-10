Feature: Payment
  Scenario: Successful Payment
    Given a customer with name "Susan", who is registered with Simple DTU Pay
    Given a merchant with name "Daniel", who is registered with Simple DTU Pay
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is successful
    
  Scenario: List of payments
    Given a customer with name "Susan", who is registered with Simple DTU Pay
    And a merchant with name "Daniel", who is registered with Simple DTU Pay
    Given a successful payment of "10" kr from the customer to the merchant
    When the manager asks for a list of payments
    Then the list contains a payments where customer "Susan" paid "10" kr to merchant "Daniel"
  
  Scenario: Customer is not known
    Given a merchant with name "Daniel", who is registered with Simple DTU Pay
    When the merchant initiates a payment for "10" kr using customer id "2fcc0634-10e4-4a3e-80d6-b543e616c9e4"
    Then the payment is not successful
    And an error message is returned saying "customer with id \"2fcc0634-10e4-4a3e-80d6-b543e616c9e4\" is unknown"