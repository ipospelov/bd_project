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
        if(notFulfilled.isEmpty()) {
            Order order = client.addOrder();
            order.addProduct(product);
        }else {
            notFulfilled.get(0).addProduct(product);
        }

        show(name);
    }

    public static void showBasket(){
        Client currentClient = (Client) Client.findAll().get(0);
        List<Order> currentOrders = currentClient.orders.stream().filter(s->s.fulfilled!=true && s.confirmed!=true).collect(Collectors.toList());
        //System.out.println(currentOrders.size());
        //System.out.println(currentOrders.get(0).products.size());
        Order currentOrder;
        List<Product> products;
        if(currentOrders.isEmpty()) {
            currentOrder = null;
            products = null;
        }else {
            currentOrder = currentOrders.get(0);
            products = currentOrder.products;
        }

        render(products, currentOrder);
    }

    public static void addOrder(Product product, Client client){
        Order order = client.addOrder();
        //Order currentOrder = new Order(client).save();
        //currentOrder.setClient(client);
        order.addProduct(product);
    }

    public static void supervisor(){
        List<Client> clients = Client.findAll();
        List<Client> currentClients = clients.stream().filter(s->
            (s.orders.stream().filter(t->t.fulfilled != true && t.confirmed == true).collect(Collectors.toList()).size() > 0)
        ).collect(Collectors.toList());
        List<Client> clientList = new ArrayList<>(currentClients);
        render(clientList);
    }

    public static void showOrderInfo(Long id){
        Client client = Client.findById(id);
        List<Order> currentOrders = client.orders.stream().filter(s->s.fulfilled!=true && s.confirmed==true).collect(Collectors.toList());
        List<Product> products = currentOrders.get(0).products;
        render(client, products);
    }

    public static void clearBasket(Long id){
        Client client = Client.findById(id);
        List<Order> currentOrders = client.orders.stream().filter(s->s.fulfilled!=true && s.confirmed == true).collect(Collectors.toList());
        currentOrders.forEach(s->s.markAsDone());
        supervisor();
    }

    public static void deleteProduct(Long productId, Long orderId){
        Order order = (Order)Order.findById(orderId);
        order.deleteProduct(productId);
        if(order.products.isEmpty())
            order.delete();
        showBasket();
    }

    public static void confirmOrder(Long orderId){
        ((Order)Order.findById(orderId)).markAsConfirmed();
        index();
    }

}