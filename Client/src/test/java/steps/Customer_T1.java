package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import services.CustomerService;

import static org.junit.Assert.assertEquals;

public class Customer_T1 {
    private Customer customer;
    CustomerService customerService = new CustomerService();

    @When("a customer with name {string}")
    public void a_customer_with_name(String string) {
        customer = customerService.createCustomer(string);
        return;
    }
    @Then("it returns the Customer object with name {string}")
    public void it_returns_the_customer_object_with_name(String string) {
        assertEquals(customer.getName(), string);
    }
}
