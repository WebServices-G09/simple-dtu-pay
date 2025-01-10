package services;

import dtu.ws.fastmoney.*;
import services.interfaces.IBankService;

import java.math.BigDecimal;

public class BankServiceImplement implements IBankService {

    @Override
    public String createAccount(String firstName, String lastName, String cpr, BigDecimal initialBalance) {
        try {
            // Initialize the generated service class
            BankServiceService bankServiceService = new BankServiceService();
            BankService bankService = bankServiceService.getBankServicePort();

            // Create a new User object
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCprNumber(cpr);

            // Call the SOAP method to create the account with the specified balance
            String accountId = bankService.createAccountWithBalance(user, initialBalance);

            // Return the account ID
            return accountId;

        } catch (BankServiceException_Exception e) {
            // Handle exception if account creation fails
            e.printStackTrace();
            throw new RuntimeException("Account creation failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Account getAccount(String accountId) throws BankServiceException_Exception {
        BankServiceService bankServiceService = new BankServiceService();
        BankService bankService = bankServiceService.getBankServicePort();
        return bankService.getAccount(accountId);
    }

}
