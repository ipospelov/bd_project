package controllers;

import models.Product;
import play.*;
import play.db.jpa.JPABase;
import play.mvc.*;

import java.util.*;


public class Application extends Controller {

    public static void index() {
        HashSet<String> productTypes = new HashSet<String>();
       List<Product> storeProducts = Product.findAll();
       for(Product product : storeProducts) {
           productTypes.add(product.type);
       }
       render(productTypes, storeProducts);
    }

    public static void sayHello(String myName) {
        render(myName);
    }

    public static void show(String name){
        Product product = (Product) Product.find("byName", name).first();
        render(product);
    }

    public static void addToBasket(String name){
        show(name);
    }

}