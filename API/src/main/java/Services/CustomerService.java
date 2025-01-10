package Services;

import Services.Interfaces.ICustomerService;
import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import models.Customer;

import java.util.HashMap;
import java.util.UUID;

public class CustomerService implements ICustomerService {
    private final HashMap<UUID, Customer> customers = new HashMap<>();
    private final BankService bankService;

    public CustomerService() {
        this.bankService = new BankServiceService().getBankServicePort();
    }

    @Override
    public Customer createCustomer(String name) {
        try {
            // Splitting name into first and last (Assumes a "First Last" format)
            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts.length > 1 ? nameParts[1] : "";

            // Create bank account for the customer
            var bankUser = new dtu.ws.fastmoney.User();
            bankUser.setFirstName(firstName);
            bankUser.setLastName(lastName);
            bankUser.setCprNumber(UUID.randomUUID().toString().substring(0, 10)); // Mock CPR for testing
            String accountId = bankService.createAccountWithBalance(bankUser, 1000);

            // Create and store customer
            Customer customer = new Customer(name);
            customer.setBankAccount(accountId);
            customers.put(customer.getId(), customer);
            return customer;

        } catch (BankServiceException_Exception e) {
            System.err.println("Error creating customer bank account: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customers.get(id);
    }

    @Override
    public boolean deleteCustomer(UUID id) {
        if (customers.containsKey(id)) {
            customers.remove(id);
            return true;
        }
        return false;
    }
}
