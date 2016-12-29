
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
public class BoardTest {
    
    public BoardTest() {
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
     * Test of setBoard method, of class Board.
     */
    @Test
    public void testSetBoard() {
        System.out.println("setBoard");
        int y = 0;
        int x = 0;
        char c = 'V';
        Board instance = new Board();
        
        boolean result = instance.checkBoard(y, x, c);
        assertEquals(false, result);
        
        instance.setBoard(y, x, c);
        
        result = instance.checkBoard(y, x, c);
        assertEquals(true, result);

    
    }

    /**
     * Test of checkBoard method, of class Board.
     */
    @Test
    public void testCheckBoard() {
        System.out.println("checkBoard");
        int y = 5;
        int x = 5;
        char c = '~';
        Board instance = new Board();
        boolean result = instance.checkBoard(y, x, c);
        assertEquals(true, result);
        
       
    }

    
}
