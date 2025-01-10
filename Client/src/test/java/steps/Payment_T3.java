package steps;

import Exceptions.UserException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import models.Merchant;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;

import java.util.UUID;

import static org.junit.Assert.*;

public class Payment_T3 {
    private UUID customerId;
    private Customer customer;
    CustomerService customerService = new CustomerService();

    @Given("a customer with name {string}, who is registered with Simple DTU Pay")
    
    customer = customerService.createCustomer(name);
	assertNotNull(customerService.getCustomerById(customer.getId()));
	
    }
    
    @Given("a merchant with name {string}, who is registered with Simple DTU Pay")
    public void aMerchantWithNameWhoIsRegisteredWithSimpleDTUPay(String string) {
    	
    merchant = merchantService.createMerchant(name);
    assertNotNull(merchantService.getMerchantById(merchant.getId()));
    	
    }
    
    @Given("a successful payment of {string} kr from the customer to the merchant")
    public void aSuccessfulPaymentOfKrFromTheCustomerToTheMerchant(String string) {
    	paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), amount);
        assertNotNull(paymentId);
        boolean result = paymentService.pay(paymentId, amount);
        assertTrue(result);
    }
   
    @When("the merchant initiates a payment for {string} kr using customer id {string}")
    public void theMerchantInitiatesAPaymentForKrUsingCustomerId(String string, String string2) {
        paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), amount);
        assertNotNull(paymentId);
    }

    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        
    }

    @Then("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSaying(String string) {
        
    }
}
