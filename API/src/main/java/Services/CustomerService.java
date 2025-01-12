package Services;

import Exceptions.UserException;
import Services.Interfaces.ICustomerService;
import models.Customer;
import models.dto.CustomerRequestDto;

import java.util.HashMap;
import java.util.UUID;

public class CustomerService implements ICustomerService {
    private static HashMap<UUID, Customer> customers = new HashMap<>();

    public CustomerService(){}

    @Override
    public Customer createCustomer(CustomerRequestDto customerRequest) {
        var customer = new Customer(
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getCpr(),
                customerRequest.getBankAccountNumber()
        );

        customers.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(UUID id) throws UserException {
        var customer = customers.get(id);

        if (customer == null) {
            String error = String.format("customer with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        return customer;
    }

    @Override
    public boolean deleteCustomer(UUID id) throws UserException {
        if(!customers.containsKey(id)){
            String error = String.format("customer with id \"%s\" is unknown", id);
            throw new UserException(error);
        }

        PaymentService.cancelCustomerPayments(id);
        customers.remove(id);
        return true;
    }

    public static HashMap<UUID, Customer> getCustomers() {
        return customers;
    }
}