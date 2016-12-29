
package LogicClient;

import GUI.GameUI;
import static LogicClient.Player.submarine;
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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getSubmarine method, of class Player.
     */
    @Test
    public void testGetSubmarine() {
        System.out.println("getSubmarine");
        Player instance = new Player("Ilia");
        Ship result = instance.getSubmarine();
        Ship expected= new Ship(3,2,"Submarine");
        assertEquals("Submarine", result.getName());
        assertEquals(3, result.getSize());
   
    }

    /**
     * Test of hit method, of class Player.
     */
    @Test
    public void testHit() {
        System.out.println("hit");
        
        Player instance = new Player("Ilia");
        
        
         assertEquals(0,instance.getHitCount());
        instance.hit();
      
         assertEquals(1,instance.getHitCount());
    }

    /**
     * Test of miss method, of class Player.
     */
    @Test
    public void testMiss() {
        System.out.println("miss");
        Player instance = new Player("Ilia");
        
        
         assertEquals(0,instance.getMissCount());
          instance.miss();
      
         assertEquals(1,instance.getMissCount());
    }

    /**
     * Test of checkWinner method, of class Player.
     */
    @Test
    public void testCheckWinner() {
        System.out.println("checkWinner");
       
        Player instance = new Player("Ilia");
        boolean result = instance.checkWinner();
        assertEquals(false, result);
        
        for(int i=0;i<17;i++)
            instance.hit();
       
        result = instance.checkWinner();
        assertEquals(true, result);
        
    }
    
      /**
     * Test of getfirstplay method, of class Player.
     */
    @Test
    public void testGetfirstplay() {
        System.out.println("getfirstplay");
        Player instance = new Player("Ilia");
        boolean result = instance.getfirstplay();
        assertEquals(false, result);
        
        
    }

    /**
     * Test of setfirstplay method, of class Player.
     */
    @Test
    public void testSetfirstplay() {
        System.out.println("setfirstplay");
      
        Player instance = new Player("Ilia");
        boolean result = instance.getfirstplay();
        assertEquals(false, result);
        
        instance.setfirstplay();
        
        result = instance.getfirstplay();
        assertEquals(true, result);
    }

  


    /**
     * Test of checkHitBoard method, of class Player.
     */
    @Test
    public void testCheckHitBoard() {
        System.out.println("checkHitBoard");
        int y = 5;
        int x = 5;
        char c = '~';
        Player instance = new Player("Ilia");
        boolean result = instance.checkHitBoard(y, x, c);
        assertEquals(true, result);
        
        result = instance.checkHitBoard(y, x, 'V');
        assertEquals(false, result);
    }

    /**
     * Test of checkShipBoard method, of class Player.
     */
    @Test
    public void testCheckShipBoard() {
        System.out.println("checkShipBoard");
        int y = 5;
        int x = 5;
        char c = '~';
        Player instance = new Player("Ilia");
        boolean result = instance.checkShipBoard(y, x, c);
        assertEquals(true, result);
        
        
    }

    /**
     * Test of setHitBoard method, of class Player.
     */
    @Test
    public void testSetHitBoard() {
        System.out.println("setHitBoard");
         int y = 0;
        int x = 0;
        char c = 'V';
        Player instance = new Player("Ilia");
        instance.setHitBoard(y, x, c);
        
        assertEquals(true,instance.checkHitBoard(y, x, c));
        
        assertEquals(false,instance.checkHitBoard(y, x, 'K'));
        
    }

    /**
     * Test of setShipBoard method, of class Player.
     */
    @Test
    public void testSetShipBoard() {
        System.out.println("setShipBoard");
        int y = 0;
        int x = 0;
        char c = 'V';
        Player instance = new Player("Ilia");
        instance.setShipBoard(y, x, c);
        
        assertEquals(true,instance.checkShipBoard(y, x, c));
        
        assertEquals(false,instance.checkShipBoard(y, x, 'K'));
        
    }

    /**
     * Test of placeShip method, of class Player.
     */
    @Test
    public void testPlaceShip() {
        System.out.println("placeShip");
        Ship boat = new Ship(2, 1, "Destroyer");
        int y = 0;
        int x = 0;
        boolean hor = true;
        Player instance = new Player("Ilia");
        
        assertEquals(false,boat.getPlaced());
        assertEquals(false,instance.checkShipBoard(y, x, 'S'));
        
        instance.placeShip(boat, y, x, hor);

        assertEquals(true,instance.checkShipBoard(y, x, 'S'));
        assertEquals(true,boat.getPlaced());
    }

    /**
     * Test of placeHitBoard method, of class Player.
     */
    @Test
    public void testPlaceHitBoard() {
        System.out.println("placeHitBoard");
        int y = 0;
        int x = 0;
        int size = 2;
        boolean hor = true;
        Player instance = new Player("Ilia");
        
        assertEquals(false,instance.checkHitBoard(y, x, 'S'));
        
        instance.placeHitBoard(y, x, size, hor);
        
        assertEquals(true,instance.checkHitBoard(y, x, 'S'));
    }

    /**
     * Test of setWinner method, of class Player.
     */
    @Test
    public void testSetWinner() {
        System.out.println("setWinner");
             Player instance = new Player("Ilia");
        
        
        boolean result = instance.getWinner();
        assertEquals(false, result);
        
        instance.setWinner();
        
         result = instance.getWinner();
        assertEquals(true, result);
        
    }

    /**
     * Test of getWinner method, of class Player.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
         
        Player instance = new Player("Ilia");
        
        
        boolean result = instance.getWinner();
        assertEquals(false, result);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = new Player("Ilia");
        String result = instance.getName();
        assertEquals("Ilia", result);
    }

    /**
     * Test of setInfo method, of class Player.
     */
    @Test
    public void testSetInfo() {
        System.out.println("setInfo");
        Ship ship = new Ship(2, 1, "Destroyer");
        int y = 3;
        int x = 3;
        String mode = "modo";
        Player instance = new Player("Ilia");
        
         String result = Player.getInfo(ship);
        assertEquals("002null", result);
        
        
        instance.setInfo(ship, y, x, mode);
        
        result = Player.getInfo(ship);
        assertEquals("332modo", result);
    }

    /**
     * Test of getInfo method, of class Player.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        Ship ship =new Ship(2, 1, "Destroyer");
        String result = Player.getInfo(ship);
        assertEquals("002null", result);
    }


 
}
