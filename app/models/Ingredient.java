package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Ingredient extends Model {
    public String name;
    public double cost;

    @Lob
    public String description;

    public Ingredient(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
}
