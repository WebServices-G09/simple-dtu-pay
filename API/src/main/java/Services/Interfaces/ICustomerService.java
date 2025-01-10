package Services.Interfaces;

import models.Customer;
import java.util.UUID;

public interface ICustomerService {
    Customer createCustomer(String name, String bankAccountNumber);
    Customer getCustomerById(UUID id);
    boolean deleteCustomer(UUID id);
}
