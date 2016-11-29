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
              
               return handlerSignup(stringUis);
           default: return null;
           case "ForgotPassword": 
               return handlerForgotPassword(stringUis);
       }
    }
    
    public String[] handlerLogin(String[] receive) throws Exception{
        int state = 0;
        String[] UsernameEPass = new String[3];
        for(String Ui : receive){ 
           //System.out.println(Ui);
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
    return new String[] {"FailedConnection", "login"};
    }
       
    
    public String[] handlerSignup(String[] receive) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
       String[] Data = new String[3];
       int state = 0;
       for(String Ui : receive){ 
          
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
    return new String[] {"FailedConnection", "signup"};
   }

    private String[] handlerForgotPassword(String[] receive) throws Exception {
        String[] ChangePass = new String[4];
        int state = 0;
        for(String Ui : receive){ 
            
            System.out.println("RECEBIDO : " +Ui);
            if(state==0){
                if(Ui.equals("ForgotPassword")){
                  
                    ChangePass[0] = Ui;
                    state=1;  
                }else{
                    System.out.println(" FORGOT_PASSWORD FAILED! ");
                    return new String[] {"ForgotPassword" , "FailedConnection","FORGOTPASSWORD_FAILED"};
                }
            }
            else if(state==1){
                if(User.confirmEmailForPassword(Ui)){
                   
                   System.out.println("user confirmEmail" +  User.confirmEmail(Ui));
                   ChangePass[1] = Ui;
                   System.out.println("DataBase_Email: " + Ui);
                   state=2 ; 
                 
                }
                else{
               System.out.println("EMAIL_NOT_FOUND "+ Ui);      //VERIFICAR ISTO
               return new String[] {"ForgotPassword", "FailedConnection","EMAIL_FAILED"};
               } 
            }
          else if(state==2){
               if(User.confirmUsername(Ui)){              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                   System.out.println("CONFIRM USERNAME FUNCIONA");
                   ChangePass[2] = Ui;
                   System.out.println("Protocol_Username: " + Ui);
                   state=3; 
                  
               }else{
               System.out.println("USERNAME_NOT_FOUND: "+ Ui);      //VERIFICAR ISTO
               return new String[] {"ForgotPassword", "FailedConnection","USERNAME_FAILED"};
               }
           }
          else if(state==3){
              
             if(User.confirmPassword(Ui)){              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                  ChangePass[3] = Ui;
                   System.out.println("Protocol_Password: " + Ui);
                   state=4;   
               }else{
               System.out.println("PASSWORD_NOT_FOUND: "+ Ui);      //VERIFICAR ISTO
               return new String[] {"ForgotPassword", "FailedConnection","PASSWORD_FAILED"};
               }
           }
            else if(state==4){
                
                    if(User.sendForgotPassword(receive)>0){
                        System.out.println("PASSWORD_SUCCESFULL");
                        return ChangePass;
                    }
                    else {
                        System.out.println("PASSWORD_FAILED");
                        return new String[]  {"ForgotPassword", "FailedConnection","FORGOTPASSWORD_FAILED"};
                    }
                   //System.out.println("FAILED");
         
            }
           
            }
        return new String[]{"FailedConnection","forgotPassword"};
    }
}