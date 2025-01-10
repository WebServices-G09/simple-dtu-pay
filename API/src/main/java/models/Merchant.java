package models;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.UUID;

public class Merchant {

    @JsonbProperty("id")
    private UUID id;

    @JsonbProperty("firstName")
    private String firstName;

    @JsonbProperty("lastName")
    private String lastName;

    @JsonbProperty("cpr")
    private int cpr;

    @JsonbProperty("bankAccountNumber")
    private int bankAccountNumber;



    public Merchant(String firstName, String lastName, int cpr, int bankAccountNumber) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Merchant(){

    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCpr() {
        return cpr;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }
}