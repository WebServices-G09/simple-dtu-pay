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

public class T1_SuccessfulPayment {
    private Customer customer;
    private Merchant merchant;
    private UUID paymentId;
    private PaymentService paymentService = new PaymentService();
    private CustomerService customerService = new CustomerService();
    private MerchantService merchantService = new MerchantService();

    @Given("a customer with name {string}")
    public void aCustomerWithName(String name) {
        customer = customerService.createCustomer(name);
    }

    @Given("the customer is registered with Simple DTU Pay")
    public void theCustomerIsRegisteredWithSimpleDTUPay() throws UserException
    {
        assertNotNull(customerService.getCustomerById(customer.getId()));
    }
    @Given("a merchant with name {string}")
    public void aMerchantWithName(String name) {
        merchant = merchantService.createMerchant(name);
    }
    @Given("the merchant is registered with Simple DTU Pay")
    public void theMerchantIsRegisteredWithSimpleDTUPay() throws UserException
    {
        assertNotNull(merchantService.getMerchantById(merchant.getId()));
    }
    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) {
        paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), amount);
        assertNotNull(paymentId);
    }
    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        boolean result = paymentService.pay(paymentId, 10);
        assertTrue(result);
    }
}
