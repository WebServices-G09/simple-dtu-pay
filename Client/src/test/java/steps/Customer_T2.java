package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import services.CustomerService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class Customer_T2 {
    private UUID customerId;
    private Customer customer;
    CustomerService customerService = new CustomerService();

    @Given("an existing customer with Id {string}")
    public void anExistingCustomerWithId(UUID string) throws UserException {
    	
    	customer = customerService.getCustomerById(string);
    	
    }

    @When("a customer with Id {string} is unregistered")
    public void aCustomerWithIdIsUnregistered(UUID string) {
    	
    	deleteCustomer(UUID)
       
    }

    @Then("a customer with Id {string} no longer exists in the customer list")
    public void aCustomerWithIdNoLongerExistsInTheCustomerList(UUID string) throws UserException {
    	UserException exception = assertThrows(UserException.class, () ->
    	customerService.getCustomerById(string));

    	assertEquals("404 not found", exception.getMessage());
        
    }
}