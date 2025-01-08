package services;

import interfaces.IDtuPayService;
import models.Customer;
import models.Merchant;
import models.Payment;

import java.util.ArrayList;
import java.util.List;

public class DtuPayService implements IDtuPayService {

    // ArrayLists to store customers, merchants, and payments
    private List<Customer> customers = new ArrayList<>();
    private List<Merchant> merchants = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    @Override
    public boolean processPayment(String customerId, double amount) {
        if (amount >= 10) {
            return true;
        }
        return false;
    }

    @Override
    public void register(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void register(Merchant merchant) {
        merchants.add(merchant);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public List<Payment> getPayments() {
        return payments;
    }
}
