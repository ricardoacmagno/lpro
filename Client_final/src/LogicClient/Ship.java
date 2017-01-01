package LogicClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <code>Ship</code> represents a ship
 *
 * @author Utilizador
 */
public class Ship {

    private final int size;
    private final int id;
    private boolean placed;
    private int x, y;
    private String mode;
    private final String name;

    /**
     * Constructor
     *
     * @param size
     * @param id
     * @param name string with the name of the ship
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

    /**
     * Method to set info
     * @param y
     * @param x
     * @param mode 
     */
    public void setInfo(int y, int x, String mode) {
        this.y = y;
        this.x = x;
        this.mode = mode;

    }

    /**
     * Method to get info  
     * @return the info
     */
    public String getInfo() {
        String toreturn = y + "" + x + "" + size + "" + mode;
        return toreturn;
    }
}
