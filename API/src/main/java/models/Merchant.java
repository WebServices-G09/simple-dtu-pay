package models;

public class Merchant {
    private int id;
    private String name;

    public Merchant(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Merchant(){}

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}