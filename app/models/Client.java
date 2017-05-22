package models;


import play.db.jpa.Model;

public class Client extends Model {
    public String name;
    public String phone;
    public String address;

    public Client(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}