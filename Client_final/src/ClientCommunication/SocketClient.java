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
 *
 * @author ilia-
 */
public class SocketClient extends Socket {
    private  Socket kkSocket = null;
    private  PrintWriter out = null;
    private  BufferedReader in = null;

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
     
    //METHOD TO RECEIVE RESPONSES FROM SERVER   
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
     
     
     
    //METHOD TO SEND RESPONSES TO SERVER
    public void toSend(String fromUser) throws IOException{
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            if (fromUser != null)   out.println(fromUser);
    }
    
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
