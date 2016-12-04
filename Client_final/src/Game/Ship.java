package Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Ship {

    private final int size;
    private final int id;
    private boolean placed;
    private final String name;

    /**
     * Constructor
     *
     * @param size
     * @param id
     * @param name
     */
    public Ship(int size, int id, String name) {
        this.size = size;
        this.id = id;
        this.name = name;
        placed = false;
    }

    /**
     * <code>getSize()</code> check the ship size
     *
     * @return an int with the size
     */
    public int getSize() {
        return size;
    }

    /**
     * <code>place()</code> place a ship
     */
    public void place() {
        placed = true;
    }

    /**
     * <code>getPlaced()</code> check if the ship is placed
     *
     * @return true if is placed
     */
    public boolean getPlaced() {
        return placed;
    }

    /**
     * <code>getName()</code> gets the name of the ship
     *
     * @return a string with the name of the ship
     */
    public String getName() {
        return name;
    }
}
