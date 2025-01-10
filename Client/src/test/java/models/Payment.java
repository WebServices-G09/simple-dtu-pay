package models;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID customerId;
    private UUID merchantId;
    private double amount;

    private PaymentStatus status;

    public Payment(UUID paymentId, UUID customerId, UUID merchantId, double amount, PaymentStatus status) {
        this.id = paymentId;
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.status = status;
    }

    public Payment(){}

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus(){
        return status;
    }
}