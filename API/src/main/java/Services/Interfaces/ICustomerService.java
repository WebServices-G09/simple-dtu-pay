package Services.Interfaces;

import Exceptions.UserException;
import models.Customer;

import java.util.UUID;

public interface ICustomerService {

    public Customer createCustomer(String fName, String lName, int cpr, int bankNumber);
    public Customer getCustomerById(UUID id) throws UserException;
    public boolean deleteCustomer(UUID id);
}