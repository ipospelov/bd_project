package models;

import play.db.jpa.Model;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

public class Order extends Model {
    @ManyToOne
    public Client client;
    public Date date;

    @ManyToMany
    public List<Product> products;

}
