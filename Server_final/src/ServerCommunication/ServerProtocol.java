package ServerCommunication;

import LogicServer.User;
import java.io.IOException;

/**
 *
 * @author ilia&magno
 */
public class ServerProtocol extends Thread{
    
    public String[] getData(String server) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
       String[] stringUis;
       stringUis = server.split("&");
       switch (stringUis[0]){
           case "Login": return handlerLogin(stringUis);
           case "Signup": 
               System.out.println(stringUis);
               return handlerSignup(stringUis);
           default: return null;
       }
    }
    
    public String[] handlerLogin(String[] receive) throws Exception{
        int state = 0;
        String[] UsernameEPass = new String[3];
        for(String Ui : receive){ 
           System.out.println(Ui);
           if(state==0){
               if(Ui.equals("Login")){
                   UsernameEPass[0] = Ui;
                   state=1;   
               }else{
               return new String[] {"Login", "FailedConnection","LOGIN_FAILED"};
               }
           }
           else if(state==1){
               if(User.confirmUsername(Ui)){              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                   UsernameEPass[1] = Ui;
                   System.out.println("Protocol_Username: " + Ui);
                   state=2;   
               }else{
               System.out.println("USERNAME_NOT_FOUND: "+ Ui);      //VERIFICAR ISTO
               return new String[] {"Login", "FailedConnection","USERNAME_FAILED"};
               }
           }
           else if(state==2){
              if(User.confirmPassword(Ui)){               //SAME AS ABOVE
                  UsernameEPass[2] = Ui;
                  System.out.println("DataBase_Hash: "+ Ui);
                  System.out.println("LOGIN_SUCCESFULL");
                  return UsernameEPass;
              }
               System.out.println("WRONG_PASSWORD");
              return new String[] {"Login", "FailedConnection","PASSWORD_FAILED"};
           }
       }
    return new String[] {"FailedConnection", "debee"};
    }
       
    
    public String[] handlerSignup(String[] receive) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
       String[] Data = new String[3];
       int state = 0;
       for(String Ui : receive){ 
           System.out.println(Ui);
           if(state==0){
               if(Ui.equals("Signup")){
                   Data[0] = Ui;
                   state=1;   
               }else{
               System.out.println("SIGNUP_FAILED");
               return new String[] {"Signup","FailedConnection","SIGNUP_FAILED"};
               }
           }
           else if(state==1){
               if(!User.confirmUsername(Ui)){              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                   Data[1] = Ui;
                   System.out.println("DataBase_Username: " + Ui);
                   state=2;   
               }else{
               System.out.println("USERNAME_ALREADY_USED: "+ Ui);      //VERIFICAR ISTO
               return new String[] {"Signup", "FailedConnection","USERNAME_FAILED"};
               }
           }
           else if(state==2){
              if(!User.confirmEmail(Ui)){               //SAME AS ABOVE
                  Data[2] = Ui;
                  System.out.println("DataBase_Email: "+ Ui);
                  state=3;
              }else{
               System.out.println("EMAIL_ALREADY_USED: " + Ui);
              return new String[] {"Signup", "FailedConnection","EMAIL_FAILED"};
              }
           }
           else if (state==3){
               //Chamar a função de escrita na base de dados através do User
                User.sendSignup(receive);
                System.out.println("SIGNUP_SUCCESFULL");
                return Data;
           }
       }
    return new String[] {"FailedConnection", "debee"};
   }
}