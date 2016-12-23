/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerCommunication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 *
 * @author Francisco
 */
public class GameServer {
    private final Socket mysocket;
    PrintWriter out;
    public GameServer(Socket socket) throws IOException{
        this.mysocket=socket;
        out = new PrintWriter(mysocket.getOutputStream(), true);
    }
    
    public void sendClient(String tosend){
        out.println(tosend);
    }
    
}