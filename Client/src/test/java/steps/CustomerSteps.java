package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import services.CustomerService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerSteps {
    private UUID customerId;
    private Customer customer;
    private String exception;
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

    @When("the Customer unregisters")
    public void theCustomerUnregisters() throws UserException {
        boolean success =  customerService.unregisterCustomer(customerId);
        assertTrue(success);
    }
    @Then("the Customer is not registred")
    public void theCustomerIsNotRegistred() throws UserException {
        String expectedError = String.format("customer with id \"%s\" is unknown", customerId);

        try {
            customer = customerService.getCustomerById(customerId);
        } catch (UserException e) {
            exception = e.getMessage();
        }

        assertEquals(expectedError, exception);
    }

}
