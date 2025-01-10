package steps;

import Exceptions.UserException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import models.Merchant;
import models.Payment;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PaymentSteps {

	 private Customer customer;
	 private Merchant merchant;
	 private UUID paymentId;
	 private ArrayList<Payment> paymentList;
     private Integer paymentAmount;
     private String exception;
	 private PaymentService paymentService = new PaymentService();
	 private CustomerService customerService = new CustomerService();
	 private MerchantService merchantService = new MerchantService();


    @Given("a customer with name {string}, who is registered with Simple DTU Pay")
    public void aCustomerWithNameWhoIsRegisteredWithSimpleDTUPay(String string) throws UserException {
    	customer = customerService.createCustomer(string);
    	assertNotNull(customerService.getCustomerById(customer.getId()));
    }
    @Given("a merchant with name {string}, who is registered with Simple DTU Pay")
    public void aMerchantWithNameWhoIsRegisteredWithSimpleDTUPay(String string) throws UserException {
    	merchant = merchantService.createMerchant(string);
    	assertNotNull(merchantService.getMerchantById(merchant.getId()));
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) throws UserException {
        paymentAmount = amount;
        paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), amount);
        assertNotNull(paymentId);
    }

    @Then("the payment is successful")
    public void thePaymentIsSuccessful() {
        boolean result = paymentService.pay(paymentId, paymentAmount);
        assertTrue(result);
    }

    @Given("a successful payment of {string} kr from the customer to the merchant")
    public void aSuccessfulPaymentOfKrFromTheCustomerToTheMerchant(String string) throws UserException {
        paymentAmount = Integer.parseInt(string);
    	paymentId = paymentService.initializePayment(customer.getId(), merchant.getId(), paymentAmount);
        assertNotNull(paymentId);
        boolean result = paymentService.pay(paymentId, paymentAmount);
        assertTrue(result);
    }
    @When("the manager asks for a list of payments")
    public void theManagerAsksForAListOfPayments() {
    	paymentList = paymentService.listPayments(customer.getId(), merchant.getId());
    }

    @Then("the list contains a payments where customer {string} paid {string} kr to merchant {string}")
    public void theListContainsAPaymentsWhereCustomerPaidKrToMerchant(String customerName, String amount, String merchantName) {
        List<Payment> completedPayment = paymentList.stream().filter(p ->
                p.getCustomerId().toString().equals(customer.getId().toString()) &&
                p.getMerchantId().toString().equals(merchant.getId().toString()) &&
                p.getAmount() == Integer.parseInt(amount)
        ).collect(Collectors.toList());

        assertTrue(!completedPayment.isEmpty());
    }

    @When("the merchant initiates a payment for {string} kr using customer id {string}")
    public void theMerchantInitiatesAPaymentForKrUsingCustomerId(String amount, String customerId) throws UserException {
        try {
            paymentService.initializePayment(UUID.fromString(customerId), merchant.getId(), Integer.parseInt(amount));
        }
        catch (UserException e) {
            exception = e.getMessage();
        }
    }

    @Then("the payment is not successful")
    public void thePaymentIsNotSuccessful() {
        assertNull(paymentId);
    }

    @Then("an error message is returned saying {string}")
    public void anErrorMessageIsReturnedSaying(String string) {
        assertEquals(exception, string);
    }
}
