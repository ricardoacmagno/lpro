
package LogicClient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilia-
 */
public class ShipTest {
    
    public ShipTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSize method, of class Ship.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Ship instance = new Ship(2,1,"Destroyer");
        int expResult = 2;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of place method, of class Ship.
     */
    @Test
    public void testPlace() {
        System.out.println("place");
        Ship instance = new Ship(2,1,"Destroyer");
        
        assertEquals(false,instance.getPlaced());
       
        instance.place();
        
        assertEquals(true,instance.getPlaced());
    }

    /**
     * Test of getPlaced method, of class Ship.
     */
    @Test
    public void testGetPlaced() {
        System.out.println("getPlaced");
        Ship instance = new Ship(2,1,"Destroyer");
        boolean expResult = false;
        boolean result = instance.getPlaced();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Ship.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        Ship instance = new Ship(2,1,"Destroyer");
        String expResult = "Destroyer";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setInfo method, of class Ship.
     */
    @Test
    public void testSetInfo() {
        System.out.println("setInfo");
        int y = 2;
        int x = 2;
        String mode = "modo";
        Ship instance = new Ship(2,1,"Destroyer");
        
        
        assertEquals("002null",instance.getInfo());
        instance.setInfo(y, x, mode);
      
        assertEquals("222modo",instance.getInfo());
    }

    /**
     * Test of getInfo method, of class Ship.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        Ship instance = new Ship(2,1,"Destroyer");
        String expResult = "002null";
        String result = instance.getInfo();
        assertEquals(expResult, result);
    }
    
}
