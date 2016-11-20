
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
    public Ship carrier;
    public Ship battleship;
    public Ship cruiser;
    public Ship submarine;
    public Ship destroyer;
    public Board ShipBoard;
    Player(String name){
        this.name=name;
        firsttoplay=false;
        carrier=new Ship(5,5, "Carrier");
        battleship=new Ship(4,4,"Battleship");
        cruiser=new Ship(3,3,"Cruiser");
        submarine=new Ship(3,2,"Submarine");
        destroyer=new Ship(2,1,"Destroyer");
        ShipBoard=new Board();
    }
    
    public void printShipBoard(){
        ShipBoard.printBoard();
    }
    public void placeBoats(){
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
    private void place(Ship ship){
        System.out.print("Placing your "+ship.getName()+": ");
        ship.print();
        System.out.println("Choose the begginning grid position and if you want to place in horizontal or vertical!");
        Scanner textscanner = new Scanner(System.in);
        Scanner intscanner = new Scanner(System.in);
        while(ship.getPlaced()==false){
            String row=textscanner.nextLine();
            int collumn=intscanner.nextInt();
            String mode=textscanner.nextLine();
            System.out.println(row+collumn+" "+mode);
            int counter=0;
            boolean allowed=true;
            if(row.charAt(0)>='A' && row.charAt(0)<='J' && collumn>=1 && collumn<=10 &&(mode.charAt(0)=='V' || mode.charAt(0)=='H')){
                int size=ship.getSize();
                int rownr=row.charAt(0)-'A';
                if(mode.charAt(0)=='V'){
                    char end = (char) (row.charAt(0) + size);
                    System.out.println(end);
                    if(end<='K'){
                        for(int c=rownr; c<rownr+size;c++){
                            if(ShipBoard.boardtable[c][collumn-1]=='S'){
                                allowed=false;
                            }
                        }
                        if(allowed==true){
                            for(int c=rownr; c<rownr+size;c++){
                            ShipBoard.boardtable[c][collumn-1]='S';
                        }
                            ship.place();
                        }
                       
                    }
                }
                    else if(mode.charAt(0)=='H'){
                        int endc=collumn+size;
                        if(endc<=11){
                            for(int c=collumn-1; c<collumn-1+size;c++){
                                if(ShipBoard.boardtable[rownr][c]=='S'){
                                    allowed=false;
                                }
                            }
                            if(allowed==true){
                                for(int c=collumn-1; c<collumn-1+size;c++){
                                    ShipBoard.boardtable[rownr][c]='S';
                            }
                                ship.place();
                            }
                    }
                }
            
        }
        if(ship.getPlaced()==false){
            System.out.println("Couldn't place your ship, try again:");
        }
        
    }
       
    
}
}

