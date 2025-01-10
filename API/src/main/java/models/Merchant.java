package models;

import java.util.UUID;

public class Merchant {
    private UUID id;
    private String name;
    private String bankAccountNumber;  
    private String bankAccount;


    public Merchant(String name, String bankAccountNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Merchant() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


}
