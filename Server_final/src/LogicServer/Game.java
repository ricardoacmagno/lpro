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
import java.util.Random;

/**
 *
 * @author Francisco
 */
public class Game {

    private static int id;
    private static String player1, player1Ships;
    private static String player2, winner, loser;
    private static Socket splayer1, splayer2;
    private static String player2Ships;
    private static GameServer p1;
    private static GameServer p2;
    private static boolean winnerbool;
    int result;
    private static int player1hit, player2hit;

    public Game(String owner, int id) {
        this.player1 = owner;
        this.player2 = "null";
        this.id = id;
        this.winner = null;
        this.loser = null;
        this.player1hit = 0;
        this.player2hit = 0;
        this.winnerbool = false;
        Random randomNum = new Random();
        this.result = randomNum.nextInt(2);
    }

    public void setOpponent(String user) throws IOException {
        player2 = user;
        newOpponent();
    }
    public String getPlayer1(){
        return player1;
    }
    public String getPlayer2(){
        return player2;
    }

    public boolean getWinnerbool() {
        return winnerbool;
    }

    public String getPlayer1Ships() {
        return player1Ships;
    }

    public String getPlayer2Ships() {
        return player2Ships;
    }

    public String getWinner() {
        return winner;
    }

    public void setSplayer1(Socket mysocket) throws IOException {
        splayer1 = mysocket;
        p1 = new GameServer(splayer1);
    }

    public void setSplayer2(Socket mysocket) throws IOException {
        splayer2 = mysocket;
        p2 = new GameServer(splayer2);
    }

    public void newOpponent() throws IOException {
        
        p1.sendClient("Warning&" + id + "&" + player2);
    }

    public String setShipsInfo(String info, String username) {
        if (username.equals(player1)) {
            player1Ships = info;
            int first;
            if (result == 0) {
                first = 0;
            } else {
                first = 1;
            }
            p2.sendClient("Ships&" + info + "&" + first);
        } else if (username.equals(player2)) {
            player2Ships = info;
            int first;
            if (result == 0) {
                first = 1;
            } else {
                first = 0;
            }
            p1.sendClient("Ships&" + info + "&" + first);
        }
        return "ok";
    }

    public int getId() {
        return id;
    }

    public static void setTurn(String result, String position, String username) throws IOException {
        if (username.equals(player1)) {

            if (result.equals("hit")) {
                player1hit++;
                System.out.println("Player 1 hits are " + player1hit);
            }
            if (player1hit >= 17) {
                winner = player1;
                loser = player2;
                winnerbool = true;
                p2.sendClient("Loser");
                p1.sendClient("Winner");
            } else {
                p2.sendClient("Turn&" + position + "&" + result);
            }
        } else if (username.equals(player2)) {
            if (result.equals("hit")) {
                player2hit++;
                System.out.println("Player 2 hits are " + player2hit);
            }
            if (player2hit >= 17) {
                winnerbool = true;
                winner = player1;
                loser = player2;
                p2.sendClient("Winner");
                p1.sendClient("Loser");
            } else {
                p1.sendClient("Turn&" + position + "&" + result);
            }
        }

    }

    public void newPrivateChat(String username, String received) {
        String tosend = "privateChat&" + username + ": " + received;
        p2.sendClient(tosend);
        p1.sendClient(tosend);
            
    }
    

}
