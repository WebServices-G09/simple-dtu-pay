package steps;

//import Exceptions.BankServiceException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import dtu.ws.fastmoney.Account;
import services.interfaces.IBankService;
import services.BankServiceImplement;
import dtu.ws.fastmoney.BankServiceException_Exception;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BankAccount_T1 {

    private String accountId;
    private Account account;
    BankServiceImplement bankService = new BankServiceImplement(); // The implementation of IBankService

    @When("a user with firstName {string} lastName {string} cpr {string} and initial balance {double} creates an account")
    public void a_user_creates_an_account(String firstName, String lastName, String cpr, double initialBalance) {
        // Create account using the IBankService
        accountId = bankService.createAccount(firstName, lastName, cpr, BigDecimal.valueOf(initialBalance));
    }

    @Then("an account with firstName {string} lastName {string} and balance {double} should be created")
    public void an_account_should_be_created(String firstName, String lastName, double balance) throws BankServiceException_Exception {
        // Retrieve the account using IBankService
        account = bankService.getAccount(accountId);

        // Assert that account details match
        assertEquals(firstName, account.getUser().getFirstName());
        assertEquals(lastName, account.getUser().getLastName());
        assertEquals(BigDecimal.valueOf(balance), account.getBalance());
    }
}
