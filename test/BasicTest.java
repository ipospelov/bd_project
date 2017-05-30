import models.Client;
import models.Ingredient;
import models.Order;
import models.Product;
import org.junit.*;
import java.util.*;
import play.test.*;
public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void createAndRetrieveProduct() {

        // Create a new user and save it
        new Product("Name1", 130,100,"type", "something").save();

        // Retrieve the user with e-mail address bob@gmail.com
        Product first = Product.find("byName", "Name1").first();

        // Test
        assertNotNull(first);
        assertEquals("Name1", first.name);
    }

    @Test
    public void fullTest(){
        if(Product.count() == 0) {
            Fixtures.loadModels("inital-data.yml");
        }


        Client client = (Client) Client.findAll().get(0);
        Order order = client.addOrder();
        order.addProduct((Product) Product.findAll().get(0));
        order.addProduct((Product) Product.findAll().get(1));

        assertEquals(1, ((Client) Client.findAll().get(0)).orders.size());
        assertEquals(1, client.orders.size());
        Client client2 = (Client) Client.findAll().get(0);
        assertEquals(client2.orders.get(0).products.size(), 1);
        System.out.println(client.orders.get(0).products.get(0).name);
        System.out.println(client.orders.get(0).products.get(1).name);

      /*  assertEquals("Василий", client.name);
        List<Order> clientOrders =  Order.findAll();
        assertEquals(0, clientOrders.size());*/
        //Product frontProduct = Product.find("order by name desc").first();
        //frontProduct.addIngredient((Ingredient) Ingredient.find("order by name desc").first());

        //assertEquals(1, frontProduct.ingredients.size());
    }

}
