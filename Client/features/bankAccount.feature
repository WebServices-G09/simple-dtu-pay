Feature: Create and verify a bank account

    Scenario: Creating a bank account with user details
      Given a customer with name "Susan", last name "Baldwin", and CPR "030154-4421"
      And the customer is registered with the bank with an initial balance of 1000.0 kr
      And the customer is registered with Simple DTU Pay using their bank account
      And a merchant with name "Daniel", last name "Oliver", and CPR "131161-3045"
      And the merchant is registered with the bank with an initial balance of 1000.0 kr
      And the merchant is registered with Simple DTU Pay using their bank account
      When the merchant initiates a payment for 10 kr by the customer
      Then the payment is successful
      And the balance of the customer at the bank is 990 kr
      And the balance of the merchant at the bank is 1010 kr

