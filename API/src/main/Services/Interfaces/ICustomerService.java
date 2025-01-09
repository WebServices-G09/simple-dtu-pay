package Services.Interfaces;

import java.util.UUID;

public interface ICustomerService {
    public Customer createCustomer(Stirng name);
    public Customer getCustomerById(UUID id);
}