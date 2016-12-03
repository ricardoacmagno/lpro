package LogicClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientCommunication.ClientProtocol;
import static java.lang.Thread.sleep;

/**
 *
 * @author ricar
 */
public class User{
    
    //FAZER AS VERIFICAÇOES DO QUE ENVIA E DO QUE RECEBE
    private String Username = null;
    private String Password = null;
    private String Mail = null;
    private String Name = null;
    private String OldPassword = null;
    private int resultadoLogin=-1;
    private int resultadoPassword=-1;
    private Game game;
    
    ClientProtocol client;

   /**
    * Constructor
    * @param Username
    * @param Password
    * @param Mail
    * @param Name 
     * @param OldPassword 
    */
    public User(String Username, String Password, String Mail, String Name, String OldPassword){
        this.Username = Username;
        this.Password = Password;
        this.Mail = Mail;
        this.Name = Name;
        this.OldPassword= OldPassword;
        client = new ClientProtocol();
    }


   /**
    * <code>sendData()</code> is responsible for invoking the protocol, sending the necessary data
    * Also responsible for identifying if the information was correctly acknowledged by the database or if an error occurred 
    * @param ack    Flag to identify what type of information is being sent
    * @throws IOException
    * @throws InterruptedException 
    */
    public void sendData(String ack) throws IOException, InterruptedException{
        /*new Thread(){     
            @Override
            public void run() {
                
        }.start();
        
        sleep(100);*/
        
        System.out.println("mail:" + Mail + " Username : " + Username+" Pass: "+Password + " oldpassword:" +OldPassword);
        
        if(ack.equals("Login")) client.sendLogin(Username, Password);
        else if (ack.equals("Signup"))  client.sendSignUp(Name, Mail, Username, Password);
        else if (ack.equals("ForgotPassword")) client.sendChangePassword(Username , Password, Mail,  OldPassword);
        else if(ack.equals("check")) client.checkJoinedGame(Username);
        else if(ack.equals("checkopponent")) client.checkOpponent(game.getId());
        try {
            ArrayList<String> dataReceived=null;
            dataReceived = client.hear();
            if(dataReceived!=null)
                if("Login".equals(dataReceived.get(0))){
                    if("Erro".equals(dataReceived.get(1))){
                        if ("Username".equals(dataReceived.get(2))){
                            resultadoLogin=2;
                            client.Disconnect();
                        }
                        else if ("Password".equals(dataReceived.get(2))){
                            resultadoLogin= 3;
                            client.Disconnect();
                        }
                    }
                    else if ("OK".equals(dataReceived.get(1)))
                        if(Username.equals(dataReceived.get(2))){
                            resultadoLogin= 1;
                            client.Disconnect();
                        }
                }else if ("Signup".equals(dataReceived.get(0))){
                    if("Erro".equals(dataReceived.get(1))){
                        if ("Username".equals(dataReceived.get(2))){
                            resultadoLogin=4;
                            client.Disconnect();
                        }
                        else if ("Email".equals(dataReceived.get(2))){
                            resultadoLogin= 5;
                            client.Disconnect();
                        }
                    }
                    else if ("OK".equals(dataReceived.get(1)))
                        if(Username.equals(dataReceived.get(2))){
                            resultadoLogin= 1;
                            client.Disconnect();
                        }
                    }
                else if("ForgotPassword".equals(dataReceived.get(0))){
                    if("Erro".equals(dataReceived.get(1))){
                        
                        if("Email".equals(dataReceived.get(2))){
                            System.out.println("data received = " + dataReceived.get(2));
                            resultadoPassword=2;
                            client.Disconnect();
                        }
                        else if("Username".equals(dataReceived.get(2))){
                            resultadoPassword=3;
                            client.Disconnect();
                        }
                         else if("Password".equals(dataReceived.get(2))){
                            resultadoPassword=4;
                            client.Disconnect();
                        }
                        else if("NotCompatible".equals(dataReceived.get(2))){
                            resultadoPassword=5;
                            client.Disconnect();
                        }
                    }
                    else if("OK".equals(dataReceived.get(1))){
                        if(Username.equals(dataReceived.get(2))){
                            resultadoPassword=1;
                            client.Disconnect();
                        }
                    }          
                }
                else if("CheckGame".equals(dataReceived.get(0))){
                    System.out.println("Playing against "+dataReceived.get(1)+" in game id "+dataReceived.get(2));
                    int gameid=Integer.parseInt(dataReceived.get(2));
                    game=new Game(gameid,Username,dataReceived.get(1));
                    game.printthis();
                    
                    client.Disconnect();
                }
                else if("CheckOpponent".equals(dataReceived.get(0))){
                    System.out.println(game.getOpponent());
                    if("default".equals(game.getOpponent())){
                        game.setOpponent(dataReceived.get(1));
                        
                    System.out.println("New opponent "+dataReceived.get(1));
                    }
                    client.Disconnect();
                }

            }
                    
        
                
            catch(IOException | InterruptedException e){
                try {
                    client.Disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);}
        }

        /*while(resultadoLogin==-1){
        
           //System.out.println("À ESPERA DA RESPOSTA DO SERVIDOR"); 
           sleep(400);
        }
        interrupt();
    
    }*/
    
    /**
     * <code>getResultadoLogin()</code> serves to send the result of the verification made in <code>sendData()</code>
     * @return  identifier from what was received
     * @throws InterruptedException 
     */
   
    public int getResultadoLogin() throws InterruptedException{
        return resultadoLogin;
    }
    
    public int getResultadoRecoverPassword(){
        return resultadoPassword;
    }
    
    public void getGameCheck() throws IOException, InterruptedException{
        sendData("check");
                    
    }
    public void CheckOpponent() throws IOException, InterruptedException{
            sendData("checkopponent");
    }
    public String getGameOpponent(){
        return game.getOpponent();
    }
}
