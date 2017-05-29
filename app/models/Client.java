package models;


import net.sf.ehcache.search.expression.Or;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client extends Model {
    public String name;
    public String phone;
    public String address;
    @OneToMany(mappedBy="client")
    public List<Order> orders;


    public Client(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        orders = new ArrayList<>();
    }

    public Order addOrder(){
        Order order = new Order(this).save();
        this.orders.add(order);
        this.save();
        return order;
    }
}