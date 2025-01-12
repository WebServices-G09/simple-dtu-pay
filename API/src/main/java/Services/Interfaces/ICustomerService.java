package Services.Interfaces;

import Exceptions.UserException;
import models.Customer;
import models.dto.CustomerRequestDto;

import java.util.UUID;

public interface ICustomerService {

    public Customer createCustomer(CustomerRequestDto customerRequest);
    public Customer getCustomerById(UUID id) throws UserException;
    public boolean deleteCustomer(UUID id) throws UserException;
}