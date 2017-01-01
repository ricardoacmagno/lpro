/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

import static LogicClient.User.client;
import java.util.ArrayList;

/**
 * <code>Game</code> represents a game
 *
 * @author francisco
 */
public class Game {

    ArrayList<String> turns;
    private String me = null;
    private String opponent = null;
    private int GameId = 0;
    public boolean player1placed, player2placed, opponentbool;

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
        turns = new ArrayList<String>();
    }
/**
 * Method to get an opponent 
 * @return true if there is an opponent
 */
    public Boolean getOpponentBoolean() {
        return opponentbool;
    }
/**
 * Method to get the name 
 * @return the name
 */
    public String getName() {
        return me;
    }

    /**
     * <code>getOpponent()</code> checks the name of the opponent player
     *
     * @return the name of the opponent player
     */
    public String getOpponent() {
        return this.opponent;
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
        opponentbool = true;
    }

    /**
     * Method to get the game id
     * @return  the game id
     */
    public int getGameid() {
        return GameId;
    }

    /**
     * Method do send a message on the chat
     * @param user
     * @param tosend 
     */
    public void sendGameChat(String user, String tosend) {
        client.send("Chat&" + user + "&" + tosend);
    }

    /**
     * Method to get the name user
     * @return the name user
     */
    public String getMyName() {
        return me;
    }

    /**
     * Method to add a turn
     * @param myturn
     * @return 
     */
    public Boolean turnAdd(String myturn) {
        for (String element : turns) {
            if (element.equals(myturn)) {
                System.out.println("Try again");
                return false;
            }
        }
        turns.add(myturn);
        return true;
    }
}
