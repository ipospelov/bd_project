package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="store_order")
public class Order extends Model {
    @ManyToOne
    public Client client;

    public Date date;

    public boolean fulfilled;

    @ManyToMany
    public List<Product> products;

    public Order(Client client) {
        //this.description = description;
        this.client = client;
        products = new ArrayList<>();
        fulfilled = false;
    }

/*
    public Order(Client client, Date date, boolean fulfilled, List<Product> products) {
        this.client = client;
        this.date = date;
        this.fulfilled = fulfilled;
        this.products = products;
    }
*/

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void setClient(Client client){this.client = client;}

}
