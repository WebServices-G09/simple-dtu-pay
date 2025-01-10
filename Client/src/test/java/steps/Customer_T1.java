package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import services.CustomerService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Customer_T1 {
    private UUID customerId;
    private Customer customer;
    CustomerService customerService = new CustomerService();

    @When("a customer with fname {string} lName {string} cpr {int} accountNum {int} registers")
    public void a_customer_with_name(String fName, String lName, int cpr, int accountNum) {

        customerId = customerService.createCustomer(fName,lName, cpr, accountNum).getId();
    }

    @Then("a Customer with name {string} {string} has been created")
    public void aCustomerWithNameHasBeenCreated(String fName, String lName) throws UserException {
        customer = customerService.getCustomerById(customerId);
        assertEquals(customer.getFirstName(), fName);
        assertEquals(customer.getLastName(), lName);

    }


}
