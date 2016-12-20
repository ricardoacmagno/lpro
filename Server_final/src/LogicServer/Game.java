/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import ServerCommunication.GameServer;
/**
 *
 * @author Francisco
 */
public class Game {

    private final int id;
    private final String player1;
    private String player2;
    private Socket splayer1, splayer2;
    private String player1ShipInfo;
    public Game(String owner, int id) {
        this.player1 = owner;
        this.player2 = "null";
        this.id = id;
    }

    public void setOpponent(String user) {
        player2 = user;
    }
    public void setSplayer1( Socket mysocket){
        splayer1=mysocket;
    }
    public void setSplayer2( Socket mysocket){
        splayer2=mysocket;
    }
    public void newOpponent() throws IOException{
        GameServer p1=new GameServer(splayer1);
        p1.sendClient("Warning&"+id+"&"+player2);
    }
}
