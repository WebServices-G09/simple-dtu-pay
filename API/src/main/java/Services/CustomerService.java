
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
    public Customer createCustomer(String fName, String lName, String cpr, String bankNumber) {
        var customer = new Customer(fName, lName, cpr, bankNumber);
        customers.put(customer.getId(), customer);
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