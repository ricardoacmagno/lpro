package Game;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Player {

    String name = new String();
    int id;
    boolean firsttoplay;
    boolean winner;
    int playNr;
    int hitcount;
    int misscount;
    public int realc;
    public int realr;
    public Ship carrier;
    public Ship battleship;
    public Ship cruiser;
    public Ship submarine;
    public Ship destroyer;
    public Board ShipBoard;
    public Board HitBoard;
    Scanner textscanner = new Scanner(System.in);
    Scanner intscanner = new Scanner(System.in);

    /**
     * Constructor
     *
     * @param name
     */
    Player(String name) {
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
     * <code>printShipBoard()</code> prints in terminal the ship board of the
     * player
     */
    public void printShipBoard() {
        ShipBoard.printBoard();
    }

    /**
     * <code>printHiBoard()</code> prints in terminal the hits in the ship
     * board of the oponent player
     */
    public void printHitBoard() {
        HitBoard.printBoard();
    }

    /**
     * <code>checkHitBoard()</code> checks if the param c is in the position
     * x,y of the hit board
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
     * <code>setHitBoard()</code> inserts the param c in the position x,y of
     * the hit board
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
        printShipBoard();
        System.out.println("Placed " + boat.getName());
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

}
