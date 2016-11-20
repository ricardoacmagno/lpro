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
    private static int TurnNr;
    private static int heads;
    private static int tails;
    public static Player player1;
    public static Player player2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TurnNr=0;
        heads=0;
        tails=1;
        game();
        startgame();

    }

    public static int flip() {
        result = randomNum.nextInt(2);
        if (result == heads) {
            return heads;
        } else {
            return tails;
        }
    }

    public static void game() {
        String name1 = "player1";
        String name2 = "player2";
        player1 = new Player(name1);
        player2 = new Player(name2);
        player1.id = heads;
        player2.id = tails;
        if (flip() == heads) {
            player1.firsttoplay = true;
            System.out.println("Player 1 first to play!");
            System.out.println("Place your ships in this board...");
            player1.printShipBoard();
            player1.placeBoats();
            System.out.println("Player 2 second to play!");
            System.out.println("Place your ships in this board...");
            player2.printShipBoard();
            player2.placeBoats();
        } else {
            player2.firsttoplay = true;
            System.out.println("Player 2 first to play!");
            System.out.println("Place your ships in this board...");
            player2.printShipBoard();
            player2.placeBoats();
            System.out.println("Player 1 second to play!");
            System.out.println("Place your ships in this board...");
            player1.printShipBoard();
            player1.placeBoats();
        }
    }
    public static Player startgame(){
        if(player1.firsttoplay){
            while(player2.getWinner()==false && player1.getWinner()==false){
                TurnNr++;
                player1.turn();
                if(player2.ShipBoard.boardtable[player1.realr][player1.realc]=='S'){
                    player1.HitBoard.boardtable[player1.realr][player1.realc]='H';
                    player2.ShipBoard.boardtable[player1.realr][player1.realc]='H';
                }
                else{
                    player1.HitBoard.boardtable[player1.realr][player1.realc]='M';
                }    
                player1.ShipBoard.printBoard();
                player1.HitBoard.printBoard();  
                if(!(player2.getWinner()==false && player1.getWinner()==false))
                    break;
                player2.turn();
                if(player1.ShipBoard.boardtable[player2.realr][player2.realc]=='S'){
                    player2.HitBoard.boardtable[player2.realr][player2.realc]='H';
                    player1.ShipBoard.boardtable[player2.realr][player2.realc]='H';
                }
                else{
                    player2.HitBoard.boardtable[player2.realr][player2.realc]='M';
                    player1.ShipBoard.boardtable[player2.realr][player2.realc]='M';
                }    
                player2.ShipBoard.printBoard();
                player2.HitBoard.printBoard();
            }
                
        }
        else{
            while(player2.getWinner()==false && player1.getWinner()==false){
                TurnNr++;
                player2.turn();
                if(player1.ShipBoard.boardtable[player2.realr][player2.realc]=='S'){
                    player2.HitBoard.boardtable[player2.realr][player2.realc]='H';
                    player1.ShipBoard.boardtable[player2.realr][player2.realc]='H';
                }
                else{
                    player2.HitBoard.boardtable[player2.realr][player2.realc]='M';
                    player1.ShipBoard.boardtable[player2.realr][player2.realc]='M';
                }    
                player2.ShipBoard.printBoard();
                player2.HitBoard.printBoard();
                if(!(player2.getWinner()==false && player1.getWinner()==false))
                    break;
                player1.turn();
                if(player2.ShipBoard.boardtable[player1.realr][player1.realc]=='S'){
                    player1.HitBoard.boardtable[player1.realr][player1.realc]='H';
                    player2.ShipBoard.boardtable[player1.realr][player1.realc]='H';
                }
                else{
                    player1.HitBoard.boardtable[player1.realr][player1.realc]='M';
                }    
                player1.ShipBoard.printBoard();
                player1.HitBoard.printBoard(); 
            }
            
        }
        if(player1.getWinner())
            return player1;
        else
            return player2;
    }
}
