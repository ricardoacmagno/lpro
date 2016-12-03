package ServerCommunication;

import LogicServer.User;
import java.io.IOException;

/**
 * Class responsible for handling the string passed as argument from <code>MultiServerThread</code>
 * This class invokes the <code>User</code> class to verify the arguments written by the application user
 * @author ilia&magno
 */
public class ServerProtocol extends Thread{
    
    /**
     * Method responsible for calling the proper method to handle the server call based on the first argument of the <code>String</code> server
     * @param server
     * @return result of the specific method call or null in case of error
     * @throws IOException
     * @throws Exception 
     */
    public String[] getData(String server) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
       String[] stringUis;
       stringUis = server.split("&");
       switch (stringUis[0]){
           case "Login": 
               return handlerLogin(stringUis);
           case "Signup":              
               return handlerSignup(stringUis);
           default: 
               return null;
           case "ForgotPassword": 
               return handlerForgotPassword(stringUis);
           case "CheckGame":
               return handlerCheckGame(stringUis[1]);
           case "CheckOpponent":
               return handlerCheckOpponent(stringUis[1]);
       }
    }
    
    /**
     * Method that verifies the parameters inserted by the user in the login using the <code>User</code> class
     * @param receive
     * @return Vector of <code>String</code> with the parameters inserted by the user if these are verified or error message in case of failure
     * @throws Exception 
     */
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
       
    /**
     * Method that verifies if the parameters inserted by the user in the signup are already beeing used, invoking the <code>User</code> class
     * @param receive
     * @return Vector of <code>String</code> with the parameters inserted by the user if these are verified or error message in case of failure
     * @throws IOException
     * @throws Exception 
     */
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
    
    /**
     * Method that verifies the parameters inserted by the user in the change password call using the <code>User</code> class
     * @param receive
     * @return Vector of <code>String</code> with the parameters inserted by the user if these are verified or error message in case of failure
     * @throws Exception 
     */
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
    
    /**
     * 
     * @param user
     * @return
     * @throws Exception 
     */
    public String[] handlerCheckGame(String user) throws Exception{
        System.out.println("CheckGameHandler received "+user);
        String[] player = User.UserCheckGame(user);
        String[] teste= {"CheckGame",player[0],player[1]};
        return teste;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public String[] handlerCheckOpponent(String id){
        String opponent=User.CheckOpponent(id);
        String[] teste= {"CheckOpponent",opponent};
        return teste;
    }
}