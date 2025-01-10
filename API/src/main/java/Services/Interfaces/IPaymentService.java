package Services.Interfaces;

import models.Payment;

import java.util.ArrayList;
import java.util.UUID;

public interface IPaymentService
{
    public Payment initializePayment(UUID customerId, UUID merchantId, double amount);
    public boolean pay(UUID paymentId, double amount);
    public ArrayList<Payment> getPaymentList(UUID customerId, UUID merchantId);
}
