/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicServer;

import java.io.IOException;
import java.net.Socket;
import ServerCommunication.GameServer;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Francisco
 */
public class Game {

    private int id;
    private String player1, player1Ships;
    private String player2, winner, loser;
    private Socket splayer1, splayer2;
    private String player2Ships, spectatorTurn;
    private GameServer p1;
    private GameServer p2;
    private boolean winnerbool;
    private boolean ships1, ships2;
    char Separator = ((char) 007);
    int result;
    private int player1hit, player2hit;
    ArrayList<GameServer> spectators;
    int turn;

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
        this.spectators = new ArrayList<>();
        this.turn = 0;
        this.ships1 = this.ships2 = false;
        spectatorTurn = "GameTurns";
    }

    public void setOpponent(String user) throws IOException {
        player2 = user;
        newOpponent();
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
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
            ships1 = true;
            player1Ships = info;
            int first;
            if (result == 0) {
                first = 0;
            } else {
                first = 1;
            }
            p2.sendClient("Ships&" + info + "&" + first);
            if (spectators.size() > 0) {

                sendSpectators("Spectator&Ships&" + info + "&" + "player1");
            }
        } else if (username.equals(player2)) {
            ships2 = true;
            player2Ships = info;
            int first;
            if (result == 0) {
                first = 1;
            } else {
                first = 0;
            }
            p1.sendClient("Ships&" + info + "&" + first);
            if (spectators.size() > 0) {
                sendSpectators("Spectator&Ships&" + info + "&" + "player2");
            }
        }
        return "ok";
    }

    public int getId() {
        return id;
    }

    public void setTurn(String result, String position, String username) throws IOException, InterruptedException {
        turn++;
        if (username.equals(player1)) {

            if (result.equals("hit")) {
                player1hit++;
                System.out.println("Player 1 hits are " + player1hit);
            }
            if (spectators.size() > 0) {
                sendSpectators("Spectator&Turn&" + position + "&" + result + "&player1");
            }
            p2.sendClient("Turn&" + position + "&" + result);
            spectatorTurn += Separator + "Spectator&Turn&" + position + "&" + result + "&player1";
            sleep(50);
            if (player1hit >= 17) {
                winner = player1;
                sendSpectators("Spectator&Finish&" + winner);
                loser = player2;
                winnerbool = true;
                p2.sendClient("Turn&" + position + "&" + result);
                sleep(50);
                p2.sendClient("Loser");
                p1.sendClient("Winner");
            }

        } else if (username.equals(player2)) {
            if (result.equals("hit")) {
                player2hit++;
                System.out.println("Player 2 hits are " + player2hit);
            }
            if (spectators.size() > 0) {
                sendSpectators("Spectator&Turn&" + position + "&" + result + "&player2");
            }
            p1.sendClient("Turn&" + position + "&" + result);
            spectatorTurn += Separator + "Spectator&Turn&" + position + "&" + result + "&player2";
            sleep(50);
            if (player2hit >= 17) {

                winnerbool = true;
                winner = player1;
                loser = player2;
                sendSpectators("Spectator&Finish&" + winner);
                sleep(50);
                p2.sendClient("Winner");
                p1.sendClient("Loser");
            }

        }

    }

    public int getWinnerHits() {
        if (winner.equals(player1)) {
            return player1hit;
        } else if (winner.equals(player2)) {
            return player2hit;
        }
        return 0;
    }

    public int getLoserHits() {
        if (winner.equals(player1)) {
            return player2hit;
        } else if (winner.equals(player2)) {
            return player1hit;
        }
        return 0;
    }

    public String getLoser() {
        return loser;
    }

    public void newPrivateChat(String username, String received) {
        String tosend = "privateChat&" + username + ": " + received;
        p2.sendClient(tosend);
        p1.sendClient(tosend);
        sendSpectators("Spectator&" + tosend);
    }

    public void JoinSpec(Socket socket) throws IOException, InterruptedException {
        GameServer join = new GameServer(socket);
        spectators.add(join);
        System.out.println("Spectator joined");
        if (spectators.size() > 0) {
            if (ships2) {
                join.sendClient("Spectator&Ships&" + player2Ships + "&" + "player2");
            }
            sleep(25);
            if (ships1) {
                join.sendClient("Spectator&Ships&" + player1Ships + "&" + "player1");
            }
            sleep(25);
            if (turn > 0) {
                String[] all = spectatorTurn.split(Separator + "");
                int size = all.length;
                int c = 1;
                while (c < size) {
                    join.sendClient(all[c]);
                    sleep(25);
                    c++;
                }
            }
        }
    }

    public void sendSpectators(String string) {
        for (GameServer element : spectators) {
            System.out.println("Sent :" + string);
            element.sendClient(string);
        }
    }

    public void rmvSpec(Socket rmvsocket) {
        Iterator<GameServer> iter = spectators.iterator();
        while (iter.hasNext()) {
            GameServer connection = iter.next();

            if (connection.getSocket() == rmvsocket) {
                iter.remove();
                System.out.println("Spectator removed");
                break;

            }
        }
    }
}
