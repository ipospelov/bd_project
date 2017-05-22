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
        new Product("Name1", 130,100,"type").save();

        // Retrieve the user with e-mail address bob@gmail.com
        Product first = Product.find("byName", "Name1").first();

        // Test
        assertNotNull(first);
        assertEquals("Name1", first.name);
    }

}
