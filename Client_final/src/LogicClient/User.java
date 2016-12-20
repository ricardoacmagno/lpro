package LogicClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientCommunication.ClientProtocol;

import static java.lang.Thread.sleep;

/**
 * <code>User</code> represents a user
 *
 * @author ricar
 */
public class User {

    //FAZER AS VERIFICAÇOES DO QUE ENVIA E DO QUE RECEBE
    private String Username = null;
    private String Password = null;
    private String Mail = null;
    private String Name = null;
    private String OldPassword = null;
    private int resultadoLogin = -1;
    private int resultadoPassword = -1;
    public static Game game;
    public static ClientProtocol client;

    /**
     * Constructor
     *
     * @param Username string with the user username
     * @param Password string wiith the user password
     * @param Mail string with the user mail
     * @param Name string with the user name
     * @param OldPassword string with the old password
     */
    public User(String Username, String Password, String Mail, String Name, String OldPassword) {
        this.Username = Username;
        this.Password = Password;
        this.Mail = Mail;
        this.Name = Name;
        this.OldPassword = OldPassword;
        client = new ClientProtocol();
    }

    /**
     * <code>sendData()</code> is responsible for invoking the protocol, sending
     * the necessary data. Also responsible for identifying if the information
     * was correctly acknowledged by the database or if an error occurred
     *
     * @param ack Flag to identify what type of information is being sent
     * @throws IOException
     * @throws InterruptedException
     */
    public void sendData(String ack) throws IOException, InterruptedException {
        /*new Thread(){     
            @Override
            public void run() {
                
        }.start();
        
        sleep(100);*/

        System.out.println("mail:" + Mail + " Username : " + Username + " Pass: " + Password + " oldpassword:" + OldPassword);

        if (ack.equals("Login")) {
            client.sendLogin(Username, Password);
        } else if (ack.equals("Signup")) {
            client.sendSignUp(Name, Mail, Username, Password);
        } else if (ack.equals("ForgotPassword")) {
            client.sendChangePassword(Username, Password, Mail, OldPassword);
        } else if (ack.equals("create")) {
            client.CreateGame(Username);
        } else if (ack.equals("join")) {
            client.JoinGame(Username);
        }
        try {
            ArrayList<String> dataReceived = null;
            dataReceived = client.hear();
            if (dataReceived != null) {
                if ("Login".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {
                        if ("Username".equals(dataReceived.get(2))) {
                            resultadoLogin = 2;
                            client.disconnect();
                        } else if ("Password".equals(dataReceived.get(2))) {
                            resultadoLogin = 3;
                            client.disconnect();
                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoLogin = 1;
                            client.disconnect();
                        }
                    }
                } else if ("Signup".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {
                        if ("Username".equals(dataReceived.get(2))) {
                            resultadoLogin = 4;
                            client.disconnect();
                        } else if ("Email".equals(dataReceived.get(2))) {
                            resultadoLogin = 5;
                            client.disconnect();
                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoLogin = 1;
                            client.disconnect();
                        }
                    }
                } else if ("ForgotPassword".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {

                        if ("Email".equals(dataReceived.get(2))) {
                            System.out.println("data received = " + dataReceived.get(2));
                            resultadoPassword = 2;
                            client.disconnect();
                        } else if ("Username".equals(dataReceived.get(2))) {
                            resultadoPassword = 3;
                            client.disconnect();
                        } else if ("Password".equals(dataReceived.get(2))) {
                            resultadoPassword = 4;
                            client.disconnect();
                        } else if ("NotCompatible".equals(dataReceived.get(2))) {
                            resultadoPassword = 5;
                            client.disconnect();
                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoPassword = 1;
                            client.disconnect();
                        }
                    }
                } else if ("CreateGame".equals(dataReceived.get(0))) {
                    System.out.println("I am " + dataReceived.get(1) + " playing in game id " + dataReceived.get(2));
                    int gameid = Integer.parseInt(dataReceived.get(2));
                    game = new Game(gameid, Username);

                } else if ("JoinGame".equals(dataReceived.get(0))) {
                    if (dataReceived.get(3).equals("ok")) {
                        int gameid = Integer.parseInt(dataReceived.get(1));
                        game = new Game(gameid, Username);
                        game.setOpponent(dataReceived.get(2));
                    }
                    System.out.println("New opponent " + dataReceived.get(1));

                } else if ("Warning".equals(dataReceived.get(0))) {
                    game.setOpponent(dataReceived.get(2));
                    System.out.println("My opponent is " + dataReceived.get(2));

                }

            }

        } catch (IOException | InterruptedException e) {
            try {
                client.disconnect();
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /*while(resultadoLogin==-1){
        
           //System.out.println("À ESPERA DA RESPOSTA DO SERVIDOR"); 
           sleep(400);
        }
        interrupt();
    
    }*/
    /**
     * Method that serves to send the result of the verification made in
     * <code>sendData()</code>
     *
     * @return identifier from what was received
     * @throws InterruptedException
     */
    public int getResultadoLogin() throws InterruptedException {
        return resultadoLogin;
    }

    /**
     * Method that serves to send the result of the verification made in
     * <code>sendData()</code>
     *
     * @return 1 if everything is ok or 2 to 5 if is an error
     */
    public int getResultadoRecoverPassword() {
        return resultadoPassword;
    }

    /**
     * Method that creates a game if there isn't any game or join one
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void getGame() throws IOException, InterruptedException {
        sendData("create");

    }

    /**
     * Method that checks if the game created by the user has an opponent
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void JoinGame() throws IOException, InterruptedException {
        sendData("join");
    }

    /**
     * Method used to get the name of the opponent
     *
     * @return a string with the name of the opponent player
     */
    public Game getGamepls() {
        return game;
    }

    public String getGameOpponent() {
        return game.getOpponent();
    }
    public ClientProtocol getClient(){
        return client;
    }
    public void hearOpponent() throws IOException, InterruptedException {
        ArrayList<String> dataReceived = null;
        dataReceived = client.hear();
        if ("Warning".equals(dataReceived.get(0))) {
            game.setOpponent(dataReceived.get(2));
            System.out.println("My opponent is " + dataReceived.get(2));

        }
    }
}
