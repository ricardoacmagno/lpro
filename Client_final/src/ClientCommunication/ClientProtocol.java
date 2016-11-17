package ClientCommunication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilia-
 */
public class ClientProtocol  {
    private  boolean connect=false;
    private  SocketClient clientSocket; 
    private  final ConcurrentHashMap<Integer,Object> sleepers = new ConcurrentHashMap<>();   
   
    public  boolean connection(){
        if (connect==false){
           try{
          clientSocket = new SocketClient();
          clientSocket.openSocket();
          connect=true;
           }catch(Exception e){
               e.printStackTrace();
           }
      } return true;  
    }
    
    public  boolean okConnection(){
        return connect;
    }
    
    public  void Disconnect() throws IOException{
        if(connect){
            clientSocket.close();
            connect=false;
        }
    }
  
    public  void sendLogin(String username, String  password){
        if(connect==false) connection();
        String message = "Login&"+username+"&"+password;
                    try {
                        clientSocket.toSend(message);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
    public  void sendSignUp(String name, String mail, String username, String password){
        if(connect==false) connection();
        String message = "Signup&"+username+"&"+mail+"&"+password+"&"+name;
                    try {
                        clientSocket.toSend(message);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
        
    public  ArrayList <String> hear() throws IOException, InterruptedException {
          
        if(connect==false)  connection();
          //  return new ArrayList<> (Arrays.asList("CCTS"));
        
        String echo;
        echo = clientSocket.received();

        if(echo!=null){
        String[] tokens = echo.split("&");
        
            switch (tokens[0]){
                case "Login" : return handlerLogin(tokens);
                case "Signup"   :return handlerSignup(tokens);
                default : return null;
            } 
        }
        return null;    
    }
   
    private  ArrayList<String> handlerLogin(String[] tokens) {
        
        ArrayList<String> login;
        login = new ArrayList<>();
        int j=0;
        
        login.add(tokens[j++]);
        login.add(tokens[j++]);
        login.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));
        
        /*if (tokens[j].equals("Erro"))
            login.add(tokens[j++]);

        if(tokens[j].equals("OK"))
            login.add(tokens[j++]);*/

        return login;
    }
    
    private  ArrayList<String> handlerSignup(String[] tokens) {
        
        ArrayList<String> signup;
        signup = new ArrayList<>();
        int j=0;
        
        signup.add(tokens[j++]);
        signup.add(tokens[j++]);
        signup.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));
        
        /*if (tokens[j].equals("Erro"))
            login.add(tokens[j++]);

        if(tokens[j].equals("OK"))
            login.add(tokens[j++]);*/

        return signup;
    }
}