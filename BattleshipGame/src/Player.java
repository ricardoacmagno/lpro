
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author francisco
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

    public void hit() {
        hitcount++;
    }

    public void miss() {
        misscount++;
    }

    public boolean checkWinner() {
        return hitcount >= 17;
    }

    public void printShipBoard() {
        ShipBoard.printBoard();
    }

    public void printHitBoard() {
        HitBoard.printBoard();
    }

    public boolean checkHitBoard(int y, int x, char c) {
        return HitBoard.checkBoard(y, x, c);
    }

    public boolean checkShipBoard(int y, int x, char c) {
        return ShipBoard.checkBoard(y, x, c);
    }

    public void setHitBoard(int y, int x, char c) {
        HitBoard.setBoard(y, x, c);
    }

    public void setShipBoard(int y, int x, char c) {
        ShipBoard.setBoard(y, x, c);
    }

    public void placeBoats() {
        place(destroyer);
        printShipBoard();
        place(submarine);
        printShipBoard();
        place(cruiser);
        printShipBoard();
        place(battleship);
        printShipBoard();
        place(carrier);
        printShipBoard();

    }

    public void setWinner() {
        winner = true;
    }

    boolean getWinner() {
        return winner;
    }

    public void turn() {
        ShipBoard.printBoard();
        HitBoard.printBoard();
        System.out.println(name + " turn to play, choose letter and number:");
        String row = textscanner.nextLine();
        while (!(row.charAt(0) >= 'A' && row.charAt(0) <= 'J')) {
            System.out.println("Bad letter");
            row = textscanner.nextLine();
        }
        int collumn = intscanner.nextInt();
        while (!(collumn >= 1 && collumn <= 10)) {
            System.out.println("Bad number");
            collumn = intscanner.nextInt();
        }
        realc = collumn - 1;
        realr = (int) row.charAt(0) - 'A';

        System.out.println("Hiting " + row.charAt(0) + collumn);

    }

    public int getX() {
        return realc;
    }

    public int getY() {
        return realr;
    }

    private void place(Ship ship) {
        System.out.print("Placing your " + ship.getName() + ": ");
        ship.print();
        System.out.println("Choose the begginning grid position and if you want to place in horizontal or vertical!");
        while (ship.getPlaced() == false) {
            String row = textscanner.nextLine();
            int collumn = intscanner.nextInt();
            String mode = textscanner.nextLine();
            System.out.println(row + collumn + " " + mode);
            boolean allowed = true;
            if (row.charAt(0) >= 'A' && row.charAt(0) <= 'J' && collumn >= 1 && collumn <= 10 && (mode.charAt(0) == 'V' || mode.charAt(0) == 'H')) {
                int size = ship.getSize();
                int rownr = row.charAt(0) - 'A';
                if (mode.charAt(0) == 'V') {
                    char end = (char) (row.charAt(0) + size);
                    System.out.println(end);
                    if (end <= 'K') {
                        for (int c = rownr; c < rownr + size; c++) {
                            if (ShipBoard.checkBoard(c, collumn - 1, 'S')) {
                                allowed = false;
                            }
                        }
                        if (allowed == true) {
                            for (int c = rownr; c < rownr + size; c++) {
                                ShipBoard.setBoard(c, collumn - 1, 'S');
                            }
                            ship.place();
                        }

                    }
                } else if (mode.charAt(0) == 'H') {
                    int endc = collumn + size;
                    if (endc <= 11) {
                        for (int c = collumn - 1; c < collumn - 1 + size; c++) {
                            if (ShipBoard.checkBoard(rownr, c, 'S')) {
                                allowed = false;
                            }
                        }
                        if (allowed == true) {
                            for (int c = collumn - 1; c < collumn - 1 + size; c++) {
                                ShipBoard.setBoard(rownr, c, 'S');
                            }
                            ship.place();
                        }
                    }
                }

            }
            if (ship.getPlaced() == false) {
                System.out.println("Couldn't place your ship, try again:");
            }

        }

    }
}
