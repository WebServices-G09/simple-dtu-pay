package models;

public class Payment {
    private int issuerId; // Merchant ID
    private int payerId;  // Customer ID
    private double amount;
    private boolean successful;

    public Payment(int issuerId, int payerId, double amount, boolean successful) {
        this.issuerId = issuerId;
        this.payerId = payerId;
        this.amount = amount;
        this.successful = successful;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
