
package Services;

import Services.Interfaces;
import java.util.ArrayList;
import java.util.Hashtable;

public class CustomerService implements ICustomerService{
    HashTable<UUID, Customer> customers = new HashTable<>();
    public CustomerService(){}

    public Customer createCustomer(Stirng name){
        var customer = new Customer(name);
        customers.put(customer.id, customer);

        return customer;
    }
    public Customer getCustomerById(UUID id){
        return customers.get(id);
    }
}