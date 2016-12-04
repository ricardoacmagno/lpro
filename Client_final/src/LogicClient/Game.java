/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

/**
 *
 * @author francisco
 */
public class Game {

    private String me = null;
    private String opponent = null;
    private int GameId = 0;

    /**
     * Constructor
     *
     * @param id
     * @param player1
     * @param player2
     */
    Game(int id, String player1, String player2) {
        this.me = player1;
        this.GameId = id;
        this.opponent = player2;
    }

    /**
     * <code>getOpponent()</code> checks the name of the opponent player
     *
     * @return the name of the opponent player
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * <code>getId()</code> gets the id of the game
     *
     * @return a int with the id of the game
     */
    public int getId() {
        return GameId;
    }

    /**
     * <code>setOpponent()</code> sets a new opponent to the game
     *
     * @param name
     */
    public void setOpponent(String name) {
        this.opponent = name;
    }
}
