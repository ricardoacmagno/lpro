package LogicClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <code>Board</code> represents a player board
 *
 * @author Utilizador
 */
public class Board {

    private char boardtable[][] = new char[10][10];
    private char letters[] = new char[10];
    private int numbers[] = new int[10];
    Player player;

    /**
     * Constructor
     */
    public Board() {
        initBoard();
    }

    /**
     * <code>initBoard()</code> creates a grid that represents a player board
     */
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

    /**
     * <code>setBoard()</code> insert a char in the board
     *
     * @param y
     * @param x
     * @param c
     */
    public void setBoard(int y, int x, char c) {
        boardtable[y][x] = c;
    }

    /**
     * <code>checkBoard()</code> checks if the param c is in the position x,y
     *
     * @param y
     * @param x
     * @param c
     * @return true or false if param c is in the position x,y
     */
    public boolean checkBoard(int y, int x, char c) {
        return c == boardtable[y][x];

    }

    /**
     * <code>printBoard()</code> print in the terminal the state of the board
     */
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
