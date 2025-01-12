package services;

import dtu.ws.fastmoney.*;
import services.interfaces.IBankService;

import java.math.BigDecimal;

public class BankServiceImplement implements IBankService {

    private BankServiceService bankServiceService = new BankServiceService();
    private BankService bankService = bankServiceService.getBankServicePort();

    @Override
    public String createAccount(String firstName, String lastName, String cpr, BigDecimal initialBalance) {
        try {
            // Create a new User object
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCprNumber(cpr);

            String accountId = bankService.createAccountWithBalance(user, initialBalance);

            // Return the account ID
            return accountId;

        } catch (BankServiceException_Exception e) {
            // Handle exception if account creation fails
            e.printStackTrace();
            throw new RuntimeException(String.format("Account creation failed: %s", e.getMessage()));
        }
    }

    @Override
    public Account getAccount(String accountId) throws BankServiceException_Exception {
        return bankService.getAccount(accountId);
    }

    public void deleteAccount(String accountId) {
        try {
            bankService.retireAccount(accountId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Failed to delete account: %s", accountId));
        }
    }

}
