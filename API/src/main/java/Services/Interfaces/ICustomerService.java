package Services.Interfaces;

import models.Customer;

import java.util.UUID;

public interface ICustomerService {
    public Customer createCustomer(String name);
    public Customer getCustomerById(UUID id);
}