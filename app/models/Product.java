package models;


import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Product extends Model{
    public String name;
    public double cost;
    public int weight;
    public String type;

    public Product(String name, double cost, int weight, String type) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.type = type;
    }
}
