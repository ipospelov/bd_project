package controllers;

import models.Client;
import models.Order;
import models.Product;
import net.sf.ehcache.search.expression.Or;
import play.*;
import play.db.jpa.JPABase;
import play.mvc.*;

import java.util.*;
import java.util.stream.Collectors;


public class Application extends Controller {

    public static void index() {
        HashSet<String> productTypes = new HashSet<String>();
       List<Product> storeProducts = Product.findAll();
       for(Product product : storeProducts) {
           productTypes.add(product.type);
       }
       render(productTypes, storeProducts);
    }

    public static void show(String name){
        Product product = (Product) Product.find("byName", name).first();
        render(product);
    }

    public static void addToBasket(String name){
        Product product = Product.find("byName", name).first();
        Client client = (Client) Client.findAll().get(0);
        //finding all orders witch isn't fulfilled:
        //System.out.println(client.name + " " + Client.count());

        List<Order> notFulfilled = client.orders.stream().filter(s->s.fulfilled!=true).collect(Collectors.toList());
        if(notFulfilled.isEmpty())
            client.addOrder();
        else
            notFulfilled.get(0).addProduct(product);

/*
        List<Order> clientOrders =  Order.find("byClient", client).fetch();
        if(clientOrders.isEmpty()){
            addOrder(product, client);
        }else {
            List<Order> notFulfilled = clientOrders.stream().filter(s->s.fulfilled!=true).collect(Collectors.toList());
            if(notFulfilled.isEmpty())
                addOrder(product, client);
            else
                notFulfilled.get(0).addProduct(product);
        }*/
        show(name);
    }

    public static void addOrder(Product product, Client client){
        Order order = client.addOrder();
        //Order currentOrder = new Order(client).save();
        //currentOrder.setClient(client);
        order.addProduct(product);
    }

}