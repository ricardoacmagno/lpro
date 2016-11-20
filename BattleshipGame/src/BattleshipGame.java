/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
/**
 *
 * @author francisco
 */
public class BattleshipGame {
    static Random randomNum = new Random();
    private static int result;
    private static final int heads = 0;
    private static final int tails = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        game();
            
    }
    public static int flip() {
        result = randomNum.nextInt(2);
        if (result == heads) {
            return heads;
        } else {
            return tails;
        }
    }
    public static void game(){
        String name1 = "player1";
        String name2 = "player2";
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        player1.id=heads;
        player2.id=tails;
        if(flip()==heads){
            player1.firsttoplay=true;
            System.out.println("Player 1 first to play!");
            System.out.println("Place your ships in this board...");
            player1.printShipBoard();
            player1.placeBoats();
            System.out.println("Player 2 second to play!");
            System.out.println("Place your ships in this board...");
            player2.printShipBoard();
            player2.placeBoats();
        }
        else{
            player2.firsttoplay=true;
            System.out.println("Player 2 first to play!");
            System.out.println("Place your ships in this board...");
            player2.printShipBoard();
            player2.placeBoats();
        }
        
    }
    
}



