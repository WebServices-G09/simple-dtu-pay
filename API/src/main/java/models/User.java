package models;

public class User {
    private String name;
    private String bankAccountNumber;

    public User(String name, String bankAccountNumber) {
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
    }

    // Getters and Setters
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
}
