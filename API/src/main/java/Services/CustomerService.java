
package Services;

import Services.Interfaces.ICustomerService;
import models.Customer;

import java.util.HashMap;
import java.util.UUID;

public class CustomerService implements ICustomerService {
    HashMap<UUID, Customer> customers = new HashMap<>();
    public CustomerService(){}

    public Customer createCustomer(String name){
        var customer = new Customer(name);
        customers.put(customer.getId(), customer);

        return customer;
    }
    public Customer getCustomerById(UUID id) {
        return customers.get(id);
    }
}