package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Customer;
import models.Merchant;
import services.PaymentService;

import java.util.UUID;

import static org.wildfly.common.Assert.assertTrue;

public class T1_SuccessfulPayment {
    private Customer customer;
    private Merchant merchant;
    private UUID customerId, merchantId;
    private PaymentService paymentService = new PaymentService();
    private boolean successful = false;
//    @Given("a customer with name {string}")
//    public void aCustomerWithName(String name) {
//        //customer = new Customer(name);
//    }
//
//    @Given("the customer is registered with Simple DTU Pay")
//    public void theCustomerIsRegisteredWithSimpleDTUPay() {
//        //var returnedCustomer = paymentService.register(customer);
//        //customerId = returnedCustomer.getId();
//    }
//    @Given("a merchant with name {string}")
//    public void aMerchantWithName(String name) {
//        //merchant = new Merchant(name);
//    }
//    @Given("the merchant is registered with Simple DTU Pay")
//    public void theMerchantIsRegisteredWithSimpleDTUPay() {
//        //var returnMerchant = paymentService.register(merchant);
//        //merchantId = returnMerchant.getId();
//
//    }
//    @When("the merchant initiates a payment for {int} kr by the customer")
//    public void theMerchantInitiatesAPaymentForKrByTheCustomer(Integer amount) {
//        //successful = paymentService.pay(amount,customerId,merchantId);
//    }
//    @Then("the payment is successful")
//    public void thePaymentIsSuccessful() {
//        assertTrue(successful);
//    }
//
//

}
