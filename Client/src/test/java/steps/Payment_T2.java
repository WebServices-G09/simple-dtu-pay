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

public class Payment_T2{

	 private Customer customer;
	 private Merchant merchant;
	 private UUID paymentId;
	 private Collection<Payment> paymentList;
	 private PaymentService paymentService = new PaymentService();
	 private CustomerService customerService = new CustomerService();
	 private MerchantService merchantService = new MerchantService();


    @Given("a customer with name {string}, who is registered with Simple DTU Pay")
    public void aCustomerWithNameWhoIsRegisteredWithSimpleDTUPay(String customerName) {
    	customer = customerService.createCustomer(name);
    	assertNotNull(customerService.getCustomerById(customer.getId()));
    }
    @Given("a merchant with name {string}, who is registered with Simple DTU Pay")
    public void aMerchantWithNameWhoIsRegisteredWithSimpleDTUPay(String merchantName) {
    	merchant = merchantService.createMerchant(name);
    	assertNotNull(merchantService.getMerchantById(merchant.getId()));
    }
    @Given("a successful payment of {string} kr from the customer to the merchant")
    public void aSuccessfulPaymentOfKrFromTheCustomerToTheMerchant(Integer amount) {
    	paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), amount);
        assertNotNull(paymentId);
        boolean result = paymentService.pay(paymentId, amount);
        assertTrue(result);
    }
    @When("the manager asks for a list of payments")
    public void theManagerAsksForAListOfPayments() {
    	Collection<Payment> paymentList = paymentService.listPayments()
    }
    @Then("the list contains a payments where customer {string} paid {string} kr to merchant {string}")
    public void theListContainsAPaymentsWhereCustomerPaidKrToMerchant(String customerName, String amount, String merchantName) {
        
    	 boolean containsPayment = containspaymentsList.stream().anyMatch(paymnt -> paymentId.equals(paymnt.getUUID()) &&
    			 merchantName.equals(paymnt.getMerchantByName().getName()) &&
    			 customerName.equals(paymnt.getCustomerByName().getName()));
    	 assertFalse(containsPayment);
    }
}
