package models;


import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends Model{
    @Required
    public String name;
    @Required
    public double cost;
    @Required
    public int weight;
    @Required
    public String type;
    @Lob
    public String description;

    @ManyToMany(cascade = CascadeType.ALL) //нужен ли каскад?
    public List<Ingredient> ingredients;

    public Product(String name, double cost, int weight, String type, String description) {

        this.ingredients = new ArrayList<Ingredient>();
        this.description = description;
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.type = type;
    }

    public Product addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        this.save();
        return this;
    }
}
