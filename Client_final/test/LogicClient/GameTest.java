
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
public class GameTest {
    
    public GameTest() {
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
     * Test of getOpponentBoolean method, of class Game.
     */
    @Test
    public void testGetOpponentBoolean() {
        System.out.println("getOpponentBoolean");
        Game instance =  new Game(1,"Ilia");
        Boolean result = instance.getOpponentBoolean();
        assertEquals(false, result);
   
    }

    /**
     * Test of getName method, of class Game.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Game instance =new Game(1,"Ilia");
        String result = instance.getName();
        assertEquals("Ilia", result);
    }

    /**
     * Test of getOpponent method, of class Game.
     */
    @Test
    public void testGetOpponent() {
        System.out.println("getOpponent");
        Game instance =new Game(1,"Ilia");
     
        String result = instance.getOpponent();
        assertEquals(null, result);
    }

    /**
     * Test of getId method, of class Game.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Game instance =new Game(1,"Ilia");
        int result = instance.getId();
        assertEquals(1, result);
    }

    /**
     * Test of setOpponent method, of class Game.
     */
    @Test
    public void testSetOpponent() {
        System.out.println("setOpponent");
        String name = "Adversario";
        Game instance = new Game(1,"Ilia");
        
        String result = instance.getOpponent();
        assertEquals(null, result);
        
        instance.setOpponent(name);
         
        result = instance.getOpponent();
        assertEquals("Adversario", result);
    }

    /**
     * Test of getGameid method, of class Game.
     */
    @Test
    public void testGetGameid() {
        System.out.println("getGameid");
        Game instance = new Game(1,"Ilia");
        int result = instance.getGameid();
        assertEquals(1, result);
    }

    /**
     * Test of getMyName method, of class Game.
     */
    @Test
    public void testGetMyName() {
        System.out.println("getMyName");
        Game instance =new Game(1,"Ilia");
        String result = instance.getName();
        assertEquals("Ilia", result);
    }

    /**
     * Test of turnAdd method, of class Game.
     */
    @Test
    public void testTurnAdd() {
        System.out.println("turnAdd");
        String myturn = "turno";
        Game instance = new Game(1,"Ilia");
        Boolean result = instance.turnAdd(myturn);
        assertEquals(true, result);
        
        result = instance.turnAdd(myturn);
        assertEquals(false, result);
    }
    
}
