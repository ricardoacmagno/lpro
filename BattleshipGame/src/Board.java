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

    public char boardtable[][] = new char[10][10];
    char letters[] = new char[10];
    int numbers[] = new int[10];
    Player player;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        for (int c = 0; c < 10; c++) {
            letters[c] = (char) ('A' + c);
        }
        for (int c = 0; c < 10; c++) {
            for (int i = 0; i < 10; i++) {
                boardtable[c][i] = '~';
            }

        }
    }

    public void printBoard() {
        System.out.println();
        for (int c = 1; c <= 10; c++) {
            System.out.print(" " + c);
        }
        System.out.println();
        for (int c = 0; c < 10; c++) {
            System.out.print(letters[c]);
            for (int i = 0; i < 10; i++) {
                if (i == 0) {
                    System.out.print(boardtable[c][i]);
                } else {
                    System.out.print(" " + boardtable[c][i]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
