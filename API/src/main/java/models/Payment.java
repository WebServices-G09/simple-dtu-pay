package models;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID customerId;
    private UUID merchantId;
    private double amount;
    private String status;

    public Payment(UUID customerId, UUID merchantId, double amount) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.status = "Pending"; 
    }

    public Payment() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(UUID merchantId) {
        this.merchantId = merchantId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
