package interfaces;

import models.Customer;
import models.Merchant;

public interface IDtuPayService {
    boolean processPayment(String customerId, double amount);
    void register(Customer customer);
    void register(Merchant merchant);
}
