package LogicClient;

import ClientCommunication.ClientProtocol;
import GUI.GameUI;
import static LogicClient.User.client;
import static LogicClient.User.game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <code>Player</code> represents a player
 *
 * @author Utilizador
 */
public class Player {

    static User user;
    static ClientProtocol client;
    public static String name = new String();
    private static int id;
    private boolean firsttoplay;
    private boolean winner;
    private int playNr;
    private int hitcount;
    private int misscount;
    public static Ship carrier;
    public static Ship battleship;
    public static Ship cruiser;
    public static Ship submarine;
    public static Ship destroyer;
    public Board ShipBoard;
    public Board HitBoard;
    Scanner textscanner = new Scanner(System.in);
    Scanner intscanner = new Scanner(System.in);

    /**
     * Constructor
     *
     * @param name string with the name of the player
     */
    public Player(String name) {
        this.name = name;
        playNr = 0;
        hitcount = 0;
        misscount = 0;
        firsttoplay = false;
        winner = false;
        carrier = new Ship(5, 5, "Carrier");
        battleship = new Ship(4, 4, "Battleship");
        cruiser = new Ship(3, 3, "Cruiser");
        submarine = new Ship(3, 2, "Submarine");
        destroyer = new Ship(2, 1, "Destroyer");
        ShipBoard = new Board();
        HitBoard = new Board();
    }
/**
 * Method to get the object of the ship
 * @return the object 
 */
    public Ship getSubmarine() {
        return submarine;
    }

    /**
     * <code>hit()</code> increments a hit on the player
     */
    public void hit() {
        hitcount++;
    }

    /**
     * <code>miss()</code> increments a miss on the player
     */
    public void miss() {
        misscount++;
    }

    /**
     * <code>checkWinner()</code> checks if the player is a winner
     *
     * @return true if the hit count is higher than 17
     */
    public boolean checkWinner() {
        return hitcount >= 17;
    }
/**
 * Method to set the first to play
 */
    public void setfirstplay() {
        firsttoplay = true;
    }
/**
 * Method to get the first to play
 * @return 
 */
    public boolean getfirstplay() {
        return firsttoplay;
    }

    /**
     * <code>printShipBoard()</code> prints in terminal the ship board of the
     * player
     */
    public void printShipBoard() {
        ShipBoard.printBoard();
    }

    /**
     * <code>printHiBoard()</code> prints in terminal the hits in the ship board
     * of the oponent player
     */
    public void printHitBoard() {
        HitBoard.printBoard();
    }

    /**
     * <code>checkHitBoard()</code> checks if the param c is in the position x,y
     * of the hit board
     *
     * @param y
     * @param x
     * @param c
     * @return true if param c is in the position x,y
     */
    public boolean checkHitBoard(int y, int x, char c) {
        return HitBoard.checkBoard(y, x, c);
    }

    /**
     * <code>checkShipBoard</code> checks if the param c is the position x,y of
     * the ship board
     *
     * @param y
     * @param x
     * @param c
     * @return true if param c is in the position x,y
     */
    public boolean checkShipBoard(int y, int x, char c) {
        return ShipBoard.checkBoard(y, x, c);
    }

    /**
     * <code>setHitBoard()</code> inserts the param c in the position x,y of the
     * hit board
     *
     * @param y
     * @param x
     * @param c
     */
    public void setHitBoard(int y, int x, char c) {
        HitBoard.setBoard(y, x, c);
    }

    /**
     * <code>setShipBoard()</code> inserts the param c in the position x,y of
     * the hit board
     *
     * @param y
     * @param x
     * @param c
     */
    public void setShipBoard(int y, int x, char c) {
        ShipBoard.setBoard(y, x, c);
    }

    /**
     * <code>placeShip()</code> inserts the ships in the ship board
     *
     * @param boat
     * @param y
     * @param x
     * @param hor
     */
    public void placeShip(Ship boat, int y, int x, boolean hor) {
        if (hor == true) {
            for (int c = x; c < (x + boat.getSize()); c++) {
                setShipBoard(y, c, 'S');
            }
        } else {
            for (int c = y; c < (y + boat.getSize()); c++) {
                setShipBoard(c, x, 'S');
            }
        }
        boat.place();
        //printShipBoard();
        //System.out.println("Placed " + boat.getName());
    }

    /**
     * Method to place a hit on the board
     * @param y
     * @param x
     * @param size
     * @param hor 
     */
    public void placeHitBoard(int y, int x, int size, boolean hor) {
        if (hor == true) {
            for (int c = x; c < (x + size); c++) {
                setHitBoard(y, c, 'S');
            }
        } else {
            for (int c = y; c < (y + size); c++) {
                setHitBoard(c, x, 'S');
            }
        }
    }

    /**
     * <code>setWinner()</code> declares the winner
     */
    public void setWinner() {
        winner = true;
    }

    /**
     * <code>getWinner()</code> checks if this player is a winner
     *
     * @return true if is a winner
     */
    boolean getWinner() {
        return winner;
    }

    /**
     * Method to get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get Hit Count
     * @return  the Hit Count
     */
    public int getHitCount() {
        return hitcount;
    }

    /**
     * Method to get the miss count
     * @return the miss count
     */
    public int getMissCount() {
        return misscount;
    }

    /**
     * Method to set info of the ship
     * @param ship
     * @param y
     * @param x
     * @param mode 
     */
    public void setInfo(Ship ship, int y, int x, String mode) {
        ship.setInfo(y, x, mode);
    }

    /**
     * Method to get info of the ship
     * @param ship
     * @return 
     */
    public static String getInfo(Ship ship) {
        String toreturn = ship.getInfo();
        return toreturn;
    }

    /**
     * Method to send the boats
     * @param user
     * @param game
     * @param gameui
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void sendBoats(User user, Game game, GameUI gameui) throws IOException, InterruptedException {
        int gameid = game.getGameid();
        String infod = getInfo(destroyer);
        String infos = getInfo(submarine);
        String infoc = getInfo(cruiser);
        String infob = getInfo(battleship);
        String infoca = getInfo(carrier);
        client = user.getClient();
        user.set(gameui);
        String tosend = "Ships&" + gameid + "&" + infod + "&" + infos + "&" + infoc + "&" + infob + "&" + infoca + "&" + user.getUsername();
        client.send(tosend);
    }

    /**
     * Method to send Turn 
     * @param y
     * @param x
     * @param result
     * @param user
     * @throws IOException
     * @throws InterruptedException 
     */
    public static void sendTurn(int y, int x, String result, User user) throws IOException, InterruptedException {
        int gameid = game.getGameid();
        String tosend = "Turn&" + gameid + "&" + y + x + "&" + result + "&" + user.getUsername();
        client.send(tosend);
    }

    /**
     * Method to set the user
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }

}
