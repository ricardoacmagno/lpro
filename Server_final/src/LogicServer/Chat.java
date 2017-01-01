package LogicServer;

import ServerCommunication.GameServer;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *Class to set the connection between the chat and the server 
 * @author Francisco
 */
public class Chat {

    ArrayList<Pair> game;
    ArrayList<GameServer> connections;
    ArrayList<Integer> guests;

    /**
     *Constructor
     */
    public Chat() {
        connections = new ArrayList<>();
        game = new ArrayList<>();
        guests = new ArrayList<>();
    }

    /**
     * Method to set a new connection
     * @param socket
     * @throws IOException 
     */
    public void newConnection(Socket socket) throws IOException {
        connections.add(new GameServer(socket));
    }

    /**
     * Method do set a new guest player
     * @param socket
     * @throws IOException 
     */
    public void newGuest(Socket socket) throws IOException {
        GameServer guest = new GameServer(socket);
        connections.add(guest);
        int id = guests.size();
        guests.add(id);
        System.out.println("My guest list:");
        for (Integer element : guests) {
            System.out.println("guest" + element);
        }

        guest.sendClient("GuestLogin&guest" + id);
    }

    /**
     * Method to set a new chat
     * @param username
     * @param received 
     */
    public void newChat(String username, String received) {
        String tosend = "Chat&" + username + ": " + received;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }

    /**
     * Method to create a new game
     * @param username 
     */
    public void newGame(String username) {
        String tosend = "GameAdd&" + username;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }

    /**
     *Method to remove a game 
     * @param username 
     */
    public void rmvGame(String username) {
        String tosend = "GameRmv&" + username;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }

    /**
     *Method to remove a connection
     * @param mysocket 
     */
    public void rmvConnection(Socket mysocket) {
        Iterator<GameServer> iter = connections.iterator();
        while (iter.hasNext()) {
            GameServer connection = iter.next();

            if (connection.getSocket() == mysocket) {
                iter.remove();
                System.out.println("Removed connection from array");
                break;
            }
        }
    }

    /**
     * Method to send all running games
     * @param mysocket 
     */
    public void SendGames(Socket mysocket) {
        char Separator = ((char) 007);
        for (GameServer element : connections) {
            if (element.getSocket() == mysocket) {
                String tosend = "Spec";
                for (Pair mygame : game) {

                    tosend += "&" + mygame.getValue().getPlayer1() + Separator + "" + mygame.getValue().getPlayer2();
                }
                tosend += "&end";
                element.sendClient(tosend);
            }

        }
    }

    /**
     * Method to add a spectator
     * @param player1
     * @param player2 
     */
    public void AddSpec(String player1, String player2) {
        for (GameServer element : connections) {
            element.sendClient("SpecAdd&" + player1 + "&" + player2);
        }

    }

    /**
     * Method to remove a spectator
     * @param player1
     * @param player2 
     */
    public void RmvSpec(String player1, String player2) {
        for (GameServer element : connections) {
            element.sendClient("SpecRmv&" + player1 + "&" + player2);
        }

    }

}
