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
    private String cpr;

    @JsonbProperty("bankAccountNumber")
    private String bankAccountNumber;



    public Merchant(String firstName, String lastName, String cpr, String bankAccountNumber) {
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

    public String getCpr() {
        return cpr;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}