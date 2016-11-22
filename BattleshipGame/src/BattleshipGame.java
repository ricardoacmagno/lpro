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
    public static Player errorplayer;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TurnNr = 0;
        heads = 0;
        tails = 1;
        errorplayer = new Player("Error");
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
            Player winner = startgame(player1, player2);
            System.out.println("And the winner is " + winner.name + "!");
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
            Player winner = startgame(player2, player1);
            System.out.println("And the winner is " + winner.name + "!");
        }
    }

    public static Player startgame(Player play1st, Player play2nd) {
        if (play1st.firsttoplay) {
            while (play2nd.getWinner() == false && play2nd.getWinner() == false) {
                TurnNr++;
                play1st.turn();
                if (play2nd.checkShipBoard(play1st.getY(), play1st.getX(), 'S')) {
                    play1st.setHitBoard(play1st.getY(), play1st.getX(), 'H');
                    play2nd.setShipBoard(play1st.getY(), play1st.getX(), 'H');
                    play1st.hit();
                } else if (play2nd.checkShipBoard(play1st.getY(), play1st.getX(), '~')) {
                    play1st.setHitBoard(play1st.getY(), play1st.getX(), 'M');
                    play2nd.setShipBoard(play1st.getY(), play1st.getX(), 'M');
                    play1st.miss();
                }
                play1st.printShipBoard();
                play1st.printHitBoard();
                if (play1st.checkWinner()) {
                    play1st.setWinner();
                    break;
                }
                play2nd.turn();
                if (play1st.checkShipBoard(play2nd.getY(), play2nd.getX(), 'S')) {
                    play2nd.setHitBoard(play2nd.getY(), play2nd.getX(), 'H');
                    play1st.setShipBoard(play2nd.getY(), play2nd.getX(), 'H');
                    play2nd.hit();
                } else if (play1st.checkShipBoard(play2nd.getY(), play2nd.getX(), '~')) {
                    play2nd.setHitBoard(play2nd.getY(), play2nd.getX(), 'M');
                    play1st.setShipBoard(play2nd.getY(), play2nd.getX(), 'M');
                    play2nd.miss();
                }
                play2nd.printShipBoard();
                play2nd.printHitBoard();
                if (play2nd.checkWinner()) {
                    play2nd.setWinner();
                    break;
                }
            }

        } else {
            System.out.println("Error getting 1st to play player!");
        }
        if (play1st.getWinner()) {
            return play1st;
        } else if (play2nd.getWinner()) {
            return play2nd;
        }
        return errorplayer;
    }
}
