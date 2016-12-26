package LogicServer;

import ServerCommunication.GameServer;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

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

    public Chat() {
        connections = new ArrayList<>();
        game = new ArrayList<>();
    }
    public void newConnection(Socket socket) throws IOException{
        connections.add(new GameServer(socket));
    }
    public void newChat(String username, String received) {
        String tosend= "Chat&"+username+": "+received;
        for(GameServer element : connections){
            element.sendClient(tosend);
        }
    }
    public void newGame(String username){
        String tosend= "GameAdd&"+username;
        for(GameServer element : connections){
            element.sendClient(tosend);
        }
    }
     public void rmvGame(String username){
        String tosend= "GameRmv&"+username;
        for(GameServer element : connections){
            element.sendClient(tosend);
        }
    }
    
    
    
}
