package steps;

//import Exceptions.BankServiceException;
import dtu.ws.fastmoney.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import dtu.ws.fastmoney.Account;
import models.Customer;
import models.Merchant;
import models.dtos.UserRequestDto;
import services.CustomerService;
import services.MerchantService;
import services.PaymentService;
import services.interfaces.IBankService;
import services.BankServiceImplement;
import dtu.ws.fastmoney.BankServiceException_Exception;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankAccount_T1 {

    private String accountId;
    private Account account;
    private User userCustomer;
    private User userMerchant;
    private Customer customer;
    private Merchant merchant;
    private UUID paymentId;
    private boolean isPaymentSuccessful = false;
    private Account customerAccount;
    private Account merchantAccount;
    BankServiceImplement bankService = new BankServiceImplement();
    CustomerService customerService = new CustomerService();
    MerchantService merchantService = new MerchantService();
    PaymentService paymentService = new PaymentService();


    @Given("a customer with name {string}, last name {string}, and CPR {string}")
    public void a_customer_with_name_last_name_and_cpr(String firstName, String lastName, String cpr) {
        userCustomer.setFirstName(firstName);
        userCustomer.setLastName(lastName);
        userCustomer.setCprNumber(cpr);
    }
    @Given("the customer is registered with the bank with an initial balance of {double} kr")
    public void the_customer_is_registered_with_the_bank_with_an_initial_balance_of_kr(Double balance) {
        accountId = bankService.createAccount(
                userCustomer.getFirstName(),
                userCustomer.getLastName(),
                userCustomer.getCprNumber(),
                new BigDecimal(balance)
        );
    }
    @Given("the customer is registered with Simple DTU Pay using their bank account")
    public void the_customer_is_registered_with_simple_dtu_pay_using_their_bank_account() {
        UserRequestDto payloadUser = new UserRequestDto();
        payloadUser.setFirstName(userCustomer.getFirstName());
        payloadUser.setLastName(userCustomer.getLastName());
        payloadUser.setCpr(userCustomer.getCprNumber());
        payloadUser.setBankAccountNumber(accountId);

        customer = customerService.createCustomer(payloadUser);
    }
    @Given("a merchant with name {string}, last name {string}, and CPR {string}")
    public void a_merchant_with_name_last_name_and_cpr(String firstName, String lastName, String cpr) {
        userMerchant.setFirstName(firstName);
        userMerchant.setLastName(lastName);
        userMerchant.setCprNumber(cpr);
    }
    @Given("the merchant is registered with the bank with an initial balance of {double} kr")
    public void the_merchant_is_registered_with_the_bank_with_an_initial_balance_of_kr(Double balance) {
        accountId = bankService.createAccount(
                userMerchant.getFirstName(),
                userMerchant.getLastName(),
                userMerchant.getCprNumber(),
                new BigDecimal(balance)
        );
    }
    @Given("the merchant is registered with Simple DTU Pay using their bank account")
    public void the_merchant_is_registered_with_simple_dtu_pay_using_their_bank_account() {
        UserRequestDto payloadUser = new UserRequestDto();
        payloadUser.setFirstName(userMerchant.getFirstName());
        payloadUser.setLastName(userMerchant.getLastName());
        payloadUser.setCpr(userMerchant.getCprNumber());
        payloadUser.setBankAccountNumber(accountId);

        merchant = merchantService.createMerchant(payloadUser);
    }
    @When("the merchant initiates a payment for {int} kr by the customer")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(Integer amount) {
        paymentId = paymentService.initializePayment(
                customer.getId(),
                merchant.getId(),
                amount
        );
    }
    @Then("the payment is successful")
    public void the_payment_is_successful() {
        isPaymentSuccessful = paymentService.pay(paymentId,10);
        assertTrue(isPaymentSuccessful);
    }
    @Then("the balance of the customer at the bank is {int} kr")
    public void the_balance_of_the_customer_at_the_bank_is_kr(Integer balance) {
        try {
            customerAccount = bankService.getAccount(customer.getBankAccountNumber());
        } catch (BankServiceException_Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(balance, customerAccount.getBalance());
    }
    @Then("the balance of the merchant at the bank is {int} kr")
    public void the_balance_of_the_merchant_at_the_bank_is_kr(Integer balance) {
        try{
            merchantAccount = bankService.getAccount(merchant.getBankAccountNumber());
        }catch (BankServiceException_Exception e){
            throw new RuntimeException(e);
        }

        assertEquals(balance, merchantAccount.getBalance());
    }
}
