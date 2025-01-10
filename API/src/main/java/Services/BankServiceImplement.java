package Services;

import Services.Interfaces.IBankService;
import dtu.ws.fastmoney.*;


import java.math.BigDecimal;

public class BankServiceImplement implements IBankService {
    @Override
    public void transferMoney(String debtorAccountId, String creditorAccountId, BigDecimal amount, String description) {
        try {
            // Initialize the generated service class
            BankServiceService bankServiceService = new BankServiceService();
            BankService bankService = bankServiceService.getBankServicePort();

            // Call the SOAP method to transfer money between accounts
            bankService.transferMoneyFromTo(debtorAccountId, creditorAccountId, amount, description);

        } catch (BankServiceException_Exception e) {
            // Handle exception if money transfer fails
            e.printStackTrace();
            throw new RuntimeException("Money transfer failed: " + e.getMessage(), e);
        }
    }


}
