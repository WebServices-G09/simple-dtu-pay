package Services.Interfaces;

import Exceptions.UserException;
import models.Customer;

import java.util.UUID;

public interface ICustomerService {
    public Customer createCustomer(String name);
    public Customer getCustomerById(UUID id) throws UserException;
    public boolean deleteCustomer(UUID id);
}