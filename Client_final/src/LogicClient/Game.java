/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

import static LogicClient.User.game;

/**
 *<code>Game</code> represents a game
 * @author francisco
 */
public class Game {
    
    private String me = null;
    private String opponent = null;
    private int GameId = 0;
    boolean player1placed, player2placed,opponentbool;
    /**
     * Constructor
     *
     * @param id
     * @param player1 string with the name of the player 1
     * @param player2 string with the name of the player 2
     */
    Game(int id, String player1) {
        this.opponentbool = false;
        this.player1placed = false;
        this.player2placed = false;
        this.me = player1;
        this.GameId = id;
        
    }
    public Boolean getOpponentBoolean() {
        return opponentbool;
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
     * @param name string with the name of the opponent
     */
    public void setOpponent(String name) {
        this.opponent = name;
        opponentbool=true;
    }
    
    public int getGameid(){
        return GameId;
    }
}
