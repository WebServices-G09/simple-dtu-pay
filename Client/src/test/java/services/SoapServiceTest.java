package services;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService; // Generated service class
import dtu.ws.fastmoney.User; // Assuming this is the generated class
import org.junit.Test;

import java.math.BigDecimal;

public class SoapServiceTest {

    private BankService bankService;

    public SoapServiceTest() {
        // Initialize the service
        BankServiceService service = new BankServiceService(); // Generated service class
        bankService = service.getBankServicePort();  // Get the service port (concrete implementation)
    }

    @Test
    public void testCreateAccount() throws BankServiceException_Exception {
        // Create a new User object
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        // Set the balance as BigDecimal
        BigDecimal balance = new BigDecimal("1000.00");

        // Call the createAccountWithBalance method
        String accountNumber = bankService.createAccountWithBalance(user, balance);

        System.out.println("Account created with number: " + accountNumber);

        // You can add assertions to verify that the account was created successfully
    }
}
