package Services;

import Exceptions.UserException;
import Services.Interfaces.IPaymentService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import models.Customer;
import models.Merchant;
import models.Payment;
import models.PaymentStatus;

import java.math.BigDecimal;
import java.util.*;

public class PaymentService implements IPaymentService
{
    private static HashMap<UUID, Payment> payments = new HashMap<>();
    private BankServiceImplement bankService = new BankServiceImplement();
    @Override
    public Payment initializePayment(UUID customerId, UUID merchantId, double amount) {
        try {
            // Retrieve customer and merchant details
            Customer customer = CustomerService.getCustomers().get(customerId);
            Merchant merchant = MerchantService.getMerchants().get(merchantId);

            if (customer == null) {
                throw new UserException("Customer does not exist.");
            }
            if (merchant == null) {
                throw new UserException("Merchant does not exist.");
            }

            // Create payment and set bank account numbers
            Payment payment = new Payment(customerId, merchantId, amount);
            payment.setCustomerBankAccount(customer.getBankAccountNumber());
            payment.setMerchantBankAccount(merchant.getBankAccountNumber());

            System.out.println(payment);

            payments.put(payment.getId(), payment);
            return payment;

        } catch (Exception e) {
            throw new RuntimeException("Error initializing payment: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean pay(UUID paymentId, double amount) {
        Payment payment = payments.get(paymentId);

        // Validate payment amount
        if (payment.getAmount() != amount) {
            return false;
        }

        // Transfer money in the bank
        bankService.transferMoney(
                payment.getCustomerBankAccount(), // Debtor account
                payment.getMerchantBankAccount(), // Creditor account
                BigDecimal.valueOf(amount),       // Amount to transfer
                "Money is being transferred"                               // Empty description
        );

        // Update payment status
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
