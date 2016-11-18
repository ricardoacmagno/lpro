package ClientCommunication;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SocketClient represents the client side of the Client-Server connection
 * Responsible for starting this connection
 * 
 * @author ilia
 */
public class SocketClient extends Socket {
    private  Socket kkSocket = null;
    private  PrintWriter out = null;
    private  BufferedReader in = null;

    /**
     * Initializes the connection.
     * @throws IOException 
     */
     public  void openSocket() throws IOException {
     try {
         kkSocket = new Socket("localhost", 1633);
         
         System.out.println("Connected to server!");
      } catch (UnknownHostException e) {
         System.err.println("Don't know about host: gnomo.fe.up.pt");
         System.exit(1);
      } catch (IOException e) {
         System.err.println("Couldn't get I/O for the connection to: gnomo.fe.up.pt");
         System.exit(1);
      }
   }
     
     /**
      * Method responsible for receiving responses from the server
      * @return String with what was read from the server
      * @throws IOException 
      */
    
     public String received () throws IOException{
            String fromServer=null;
        try {
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            fromServer = in.readLine();
                System.out.println("Received: " + fromServer);
            
        }catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);  
        }
        return fromServer;
     }
     
     /**
      * Method responsible for sending response strings to the server
      * @param fromUser String with information to send
      * @throws IOException 
      */
    public void toSend(String fromUser) throws IOException{
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            if (fromUser != null)   out.println(fromUser);
    }
    
    /**
     * Method responsible for closing the connection and all its communication ports    
     */
    @Override
    public void close(){
       try{
            in.close();
            out.close();
            kkSocket.close();
       }catch(Exception e){
           System.err.println("Error:" + e);
       }
   }
}
