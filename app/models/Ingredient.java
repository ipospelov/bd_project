package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Ingredient extends Model {
    @Required
    public String name;
    @Required
    public double cost;

    @Lob
    public String description;

    public Ingredient(String name, double cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }
}
