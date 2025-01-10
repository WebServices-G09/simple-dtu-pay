package models;

import jakarta.json.bind.annotation.JsonbProperty;
import java.util.UUID;

public class Customer {
    @JsonbProperty("id")
    private UUID id;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("bankAccountNumber") 
    private String bankAccountNumber;

    private String bankAccount; 

    public Customer(String name, String bankAccountNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Customer() {}

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
