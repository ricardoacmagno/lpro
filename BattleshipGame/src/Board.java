/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author francisco
 */
public class Board {
    char boardtable[][] = new char[10][10];
    char letters[] = new char[10];
    int numbers[] = new int[10];
    public Board(){
        initBoard();
        
        
    }
    private void initBoard(){
        System.out.print(" ");
        for(int c = 0 ; c < 10 ; c++){
            System.out.print(c);
            letters[c] = (char) ('A' + c);
        }
        System.out.println();
        for(int c = 0 ; c < 10 ; c++){
            System.out.println(letters[c]);
            
        }
        boat carrier=new boat(5);
        boat battleship=new boat(4);
        boat cruiser=new boat(3);
        boat submarine=new boat(3);
        boat destroyer=new boat(2);
    }
    
}
