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

    @When("a customer with name {string} registers")
    public void a_customer_with_name(String string) {
        customerId = customerService.createCustomer(string).getId();
    }

    @Then("a Customer with name {string} has been created")
    public void aCustomerWithNameHasBeenCreated(String string) throws UserException {
        customer = customerService.getCustomerById(customerId);
        assertEquals(customer.getName(), string);
    }

    @When("I call the getCustomer service with name {string}")
    public void iCallTheGetCustomerService(String string) throws UserException {
        customer = customerService.getCustomerByName(string);
    }

    @Then("I get the Customer with name {string}")
    public void iGetTheCustomerWithName(String string) {
        assertEquals(customer.getName(), string);
    }
}
