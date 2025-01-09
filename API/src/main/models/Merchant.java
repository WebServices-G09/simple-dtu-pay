package models;

public class Merchant {
    private UUID id;
    private String name;

    public Merchant(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Merchant(){

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