package Services;

import Services.Interfaces.IMerchantService;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import models.Merchant;

import java.util.HashMap;
import java.util.UUID;

public class MerchantService implements IMerchantService {
    private final HashMap<UUID, Merchant> merchants = new HashMap<>();
    private final BankService bankService;

    public MerchantService() {
        this.bankService = new BankServiceService().getBankServicePort();
    }

    @Override
    public Merchant createMerchant(String name) {
        try {
            // Splitting name into first and last (Assumes a "First Last" format)
            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            // Create bank account for the merchant
            var bankUser = new dtu.ws.fastmoney.User();
            bankUser.setFirstName(firstName);
            bankUser.setLastName(lastName);
            bankUser.setCprNumber(UUID.randomUUID().toString().substring(0, 10)); // Mock CPR for testing
            String accountId = bankService.createAccountWithBalance(bankUser, 1000);

            // Create and store merchant
            Merchant merchant = new Merchant(name);
            merchant.setBankAccount(accountId);
            merchants.put(merchant.getId(), merchant);
            return merchant;

        } catch (BankServiceException_Exception e) {
            System.err.println("Error creating merchant bank account: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Merchant getMerchantById(UUID id) {
        return merchants.get(id);
    }

    @Override
    public boolean deleteMerchant(UUID id) {
        if (merchants.containsKey(id)) {
            merchants.remove(id);
            return true;
        }
        return false;
    }
}
