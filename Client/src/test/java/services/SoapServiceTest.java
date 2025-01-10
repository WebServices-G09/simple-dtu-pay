package services;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService; // Generated service class
import dtu.ws.fastmoney.User; // Assuming this is the generated class for User
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

public class SoapServiceTest {

    @Test
    public void testCreateAccount() {
        try {
            // Initialize the service class generated from the WSDL
            BankServiceService bankServiceService = new BankServiceService();
            BankService bankService = bankServiceService.getBankServicePort();

            // Create a new User object with the necessary details
            String firstName = "Johnsdfds";
            String lastName = "Doeefdas";
            String cprNumber = "123456-7893"; // Sample CPR number
            BigDecimal initialBalance = new BigDecimal("100330.00"); // Initial balance

            // Create a new User object (assuming the User class has setters or a constructor)
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCprNumber(cprNumber);

            // Call the createAccountWithBalance method to create the account
            String accountId = bankService.createAccountWithBalance(user, initialBalance);

            // Assert that the account ID returned is not null, meaning the account creation succeeded
            assertNotNull("Account should have been created", accountId);

            System.out.println("Account created successfully with ID: " + accountId);

        } catch (BankServiceException_Exception e) {
            // In case of a failure, print the exception and fail the test
            e.printStackTrace();
            fail("Account creation failed: " + e.getMessage());
        }
    }
}
