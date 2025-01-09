package models;

public class Customer {
    private UUID id;
    private String name;

    public Customer(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Customer(){

    }

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
}