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

    private static int id;
    private static String player1,player1Ships;
    private static String player2;
    private static Socket splayer1, splayer2;
    private static String player2Ships;
    private static GameServer p1;
    private static GameServer p2;
    public Game(String owner, int id) {
        this.player1 = owner;
        this.player2 = "null";
        this.id = id;
    }

    public void setOpponent(String user) {
        player2 = user;
    }
    public void setSplayer1( Socket mysocket) throws IOException{
        splayer1=mysocket;
        p1=new GameServer(splayer1);
    }
    public void setSplayer2( Socket mysocket) throws IOException{
        splayer2=mysocket;
        p2=new GameServer(splayer2);
    }
    public static void newOpponent() throws IOException{
        p1.sendClient("Warning&"+id+"&"+player2);
    }
    public String setCarrierInfo(String info, String username){
        if(username.equals(player1)){
            player1Ships=info;
            p2.sendClient("Ships&"+info);
        }
        else if(username.equals(player2)){
            player2Ships=info;
            p1.sendClient("Ships&"+info);
        }
        return "ok";
    }
}
