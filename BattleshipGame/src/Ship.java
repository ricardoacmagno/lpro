/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author francisco
 */
public class Ship {

    private final int size;
    private final int id;
    private boolean placed;
    private final String name;

    public Ship(int size, int id, String name) {
        this.size = size;
        this.id = id;
        this.name = name;
        placed = false;
    }

    public void print() {
        for (int c = 1; c <= size; c++) {
            System.out.print("S");
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public void place() {
        placed = true;
    }

    public boolean getPlaced() {
        return placed;
    }

    public String getName() {
        return name;
    }
}
