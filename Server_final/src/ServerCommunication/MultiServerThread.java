package ServerCommunication;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricar
 */
public class MultiServerThread extends Thread{
   private Socket socket = null;
 
   public MultiServerThread(Socket socket){
      super("MultiServerThread");
      this.socket = socket;
   }
   String[] uno = new String[3];
   int state = 0;
   @Override
   public void run() {
      try {
          
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
         String inputLine, outputLine = null;
         ServerProtocol kappakappakappa = new ServerProtocol();

        if ((inputLine = in.readLine()) != null){
            uno = kappakappakappa.getData(inputLine);
            System.out.println("inputline"  +  inputLine);
        }    
          System.out.println(uno[0]);
          if(uno[0].equals("Login")) {
                      state=1;
                  }
          else if (uno[0].equals("Signup")) {       
                  state=2;
           }
          else if(uno[0].equals("ForgotPassword")){
              state=3;
          }
          switch (state){
              case 1:
                  if(uno[1].equals("FailedConnection")){
                    if(uno[2].equals("USERNAME_FAILED")){
                        outputLine = "Login&Erro&Username";
                        break;
                    }  
                    else if(uno[2].equals("PASSWORD_FAILED")){
                        outputLine = "Login&Erro&Password";
                        break;
                    }
                  }else {outputLine="Login&OK&"+uno[1];
                  break;
                  }
              case 2:
                  if(uno[1].equals("FailedConnection")){
                    if(uno[2].equals("USERNAME_FAILED")){
                        outputLine = "Signup&Erro&Username";
                        break;
                    }  
                    else if(uno[2].equals("EMAIL_FAILED")){
                        outputLine = "Signup&Erro&Email";
                        break;
                    }
                  }else{
                      outputLine="Signup&OK&"+uno[1];
                      break;
                  }
              case 3:
                  if(uno[1].equals("FailedConnection")){
                      if(uno[2].equals("EMAIL_FAILED")){
                          outputLine = "ForgotPassword&Erro&Email";
                          break;
                      }
                  
                     else if(uno[2].equals("USERNAME_FAILED")){
                          outputLine = "ForgotPassword&Erro&Username";
                       break;
                       }
                     
                     else if(uno[2].equals("PASSWORD_FAILED")){
                        outputLine = "ForgotPassword&Erro&Password";
                       break;
                     }
                      
                     else if(uno[2].equals("FORGOTPASSWORD_FAILED")){
                         outputLine = "ForgotPassword&Erro&NotCompatible";
                         break;
                     }
                      
                  }
                  else{
                    outputLine="ForgotPassword&OK&" + uno[1];
                    break;
                  }
          }
            System.out.println(outputLine);
            out.println(outputLine);
        
         out.close();
         in.close();
         socket.close();
      } catch (IOException e) {
      System.err.println(e.getMessage());} catch (Exception ex) {
           Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
