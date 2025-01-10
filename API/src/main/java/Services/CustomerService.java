
package Services;

import Exceptions.UserException;
import Services.Interfaces.ICustomerService;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.util.HashMap;
import java.util.UUID;
public class CustomerService implements ICustomerService {
    private static HashMap<UUID, Customer> customers = new HashMap<>();

    public CustomerService(){}

    @Override
    public Customer createCustomer(String name) {
        var customer = new Customer(name);
        customers.put(customer.getId(), customer);

        return customer;
    }

    @Override
    public Customer getCustomer(String name) {
        for (Customer customer : customers.values()) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public Customer getCustomerByName(String name) throws UserException {
        var customer = getCustomer(name);

        if (customer == null) {
            throw new UserException("{\"error\": \"Customer does not exist\"}");
        }

        return customer;
    }

    @Override
    public Customer getCustomerById(UUID id) throws UserException {
        var customer = customers.get(id);

        if (customer == null) {
            throw new UserException("{\"error\": \"Customer does not exist\"}");
        }

        return customer;
    }

    @Override
    public boolean deleteCustomer(UUID id) {
        if(customers.containsKey(id)){
            PaymentService.cancelCustomerPayments(id);
            customers.remove(id);
            return true;
        }

        return false;
    }
}