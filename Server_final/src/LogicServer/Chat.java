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
 *
 * @author Francisco
 */
public class Chat {

    ArrayList<Pair> game;
    ArrayList<GameServer> connections;
    ArrayList<Integer> guests;

    public Chat() {
        connections = new ArrayList<>();
        game = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public void newConnection(Socket socket) throws IOException {
        connections.add(new GameServer(socket));
    }

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

    public void newChat(String username, String received) {
        String tosend = "Chat&" + username + ": " + received;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }

    public void newGame(String username) {
        String tosend = "GameAdd&" + username;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }

    public void rmvGame(String username) {
        String tosend = "GameRmv&" + username;
        for (GameServer element : connections) {
            element.sendClient(tosend);
        }
    }
    
    public void rmvConnection(Socket mysocket){
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

}
