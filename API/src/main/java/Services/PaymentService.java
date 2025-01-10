package Services;

import Services.Interfaces.IPaymentService;
import models.Payment;
import models.PaymentStatus;

import java.util.*;

public class PaymentService implements IPaymentService
{
    private static HashMap<UUID, Payment> payments = new HashMap<>();
    @Override
    public Payment initializePayment(UUID customerId, UUID merchantId, double amount)
    {
        Payment payment = new Payment(customerId, merchantId, amount);
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public boolean pay(UUID paymentId, double amount)
    {
        Payment payment = payments.get(paymentId);
        if(payment.getAmount() != amount) {
            return false;
        }

        payment.setStatus(PaymentStatus.SUCCESSFUL);
        payments.replace(paymentId, payment);
        return true;
    }

    public static void cancelCustomerPayments(UUID customerId)
    {
        for (Payment payment : payments.values())
        {
            if(payment.getStatus() == PaymentStatus.PENDING &&
               payment.getCustomerId() == customerId)
            {
                payment.setStatus(PaymentStatus.FAILED);
            }
        }
    }

    public static Collection<Payment> getPayments()
    {
        return payments.values();
    }
}
