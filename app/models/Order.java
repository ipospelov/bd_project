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
    public boolean confirmed;

    @ManyToMany
    public List<Product> products;

    public Order(Client client) {
        //this.description = description;
        this.client = client;
        products = new ArrayList<>();
        fulfilled = false;
        confirmed = false;
    }

    public void markAsDone(){
        fulfilled = true;
        this.save();
    }

    public void markAsConfirmed(){
        confirmed = true;
        this.save();
    }

    public void deleteProduct(Long productId){
        int i = 0;
        for(Product product : products){
            if(product.id == productId)
                break;
            i++;
        }
        products.remove(i);
        this.save();
    }

    public void addProduct(Product product){
        this.products.add(product);
        this.save();
    }

    public void setClient(Client client){this.client = client;}

}
