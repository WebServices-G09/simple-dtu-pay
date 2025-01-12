package models;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID customerId;
    private UUID merchantId;
    private String customerBankAccount; // New field for customer's bank account
    private String merchantBankAccount; // New field for merchant's bank account
    private double amount;
    private PaymentStatus status;

    // Constructor with bank account numbers
    public Payment(UUID customerId, UUID merchantId, double amount, String customerBankAccount, String merchantBankAccount) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.customerBankAccount = customerBankAccount;
        this.merchantBankAccount = merchantBankAccount;
        this.status = PaymentStatus.PENDING;
    }

    // Constructor without bank account numbers for backward compatibility
    public Payment(UUID customerId, UUID merchantId, double amount) {
        this(customerId, merchantId, amount, null, null); // Defaults bank accounts to null
    }

    public Payment() {}

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getMerchantId() {
        return merchantId;
    }

    public String getCustomerBankAccount() {
        return customerBankAccount;
    }

    public void setCustomerBankAccount(String customerBankAccount) {
        this.customerBankAccount = customerBankAccount;
    }

    public String getMerchantBankAccount() {
        return merchantBankAccount;
    }

    public void setMerchantBankAccount(String merchantBankAccount) {
        this.merchantBankAccount = merchantBankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", merchantId=" + merchantId +
                ", customerBankAccount='" + customerBankAccount + '\'' +
                ", merchantBankAccount='" + merchantBankAccount + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
