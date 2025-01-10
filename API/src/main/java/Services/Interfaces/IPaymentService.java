package Services.Interfaces;

import models.Payment;
import models.PaymentStatus;

import java.util.UUID;

public interface IPaymentService
{
    public Payment initializePayment(UUID customerId, UUID merchantId, double amount);
    public boolean pay(UUID paymentId, double amount);
}
