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
 * Class to comunicate with the client
 * @author Francisco
 */
public class GameServer {

   
    private final Socket mysocket;
    PrintWriter out;
    
    /**
     * Constructor
     * @param socket
     * @throws IOException 
     */
    public GameServer(Socket socket) throws IOException {
        this.mysocket = socket;
        out = new PrintWriter(mysocket.getOutputStream(), true);
    }

    /**
     * Method to send the client info
     * @param tosend 
     */
    public void sendClient(String tosend) {
        out.println(tosend);
    }
    
    /**
     * Method to get a socket of a client 
     * @return the socket 
     */
    public Socket getSocket(){
        return mysocket;
    }

}
