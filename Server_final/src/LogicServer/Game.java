/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Francisco
 */
public class Game {

    private final int id;
    private final String player1;
    private String player2;
    private Socket splayer1, splayer2;

    public Game(String owner, int id) {
        this.player1 = owner;
        this.player2 = "null";
        this.id = id;
    }

    public void setOpponent(String user) {
        player2 = user;
    }
    
     
}
