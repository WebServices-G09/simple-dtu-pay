package services.interfaces;

import java.math.BigDecimal;

import dtu.ws.fastmoney.Account;
import dtu.ws.fastmoney.BankServiceException_Exception;
public interface IBankService {
    /**
     * Create a bank account with an initial balance.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param cpr The CPR number of the user.
     * @param initialBalance The initial balance of the bank account.
     * @return The account ID created by the bank service.
     */
    String createAccount(String firstName, String lastName, String cpr, BigDecimal initialBalance);
    /**
     * Retrieve the account information using the account ID.
     *
     * @param accountId The account identifier.
     * @return The account information.
     * @throws BankServiceException_Exception If the account doesn't exist or another error occurs.
     */
    Account getAccount(String accountId) throws BankServiceException_Exception;
}
