package steps;

import Exceptions.UserException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import models.dtos.UserRequestDto;
import services.CustomerService;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Customer_T1 {
    private UUID customerId;
    private Customer customer;
    CustomerService customerService = new CustomerService();

    @When("a customer with fname {string} lName {string} cpr {string} accountNum {string} registers")
    public void a_customer_with_fname_l_name_cpr_account_num_registers(String fName, String lName, String cpr, String accountNum) {
        var userDto = new UserRequestDto();
        userDto.setFirstName(fName);
        userDto.setLastName(lName);
        userDto.setCpr(cpr);
        userDto.setBankAccountNumber(accountNum);

        customerId = customerService.createCustomer(userDto).getId();
    }

    @Then("a Customer with name {string} {string} has been created")
    public void aCustomerWithNameHasBeenCreated(String fName, String lName) throws UserException {
        customer = customerService.getCustomerById(customerId);
        assertEquals(customer.getFirstName(), fName);
        assertEquals(customer.getLastName(), lName);

    }


}
