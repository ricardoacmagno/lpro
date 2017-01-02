package ServerCommunication;

import LogicServer.Chat;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class that represents the thread used by all user requests Responsible for
 * handling calls made to the server by each user
 *
 * @author ricar
 */
public class MultiServerThread extends Thread {

    private Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    boolean connection = true;
    public static Chat chat;

    /**
     * Method responsible for handling the multiple calls to the server
     *
     * @param socket Socket id
     */
    public MultiServerThread(Socket socket, Chat chat) {
        super("MultiServerThread");
        this.socket = socket;
        this.chat = chat;
        connection = true;
    }

    int state = 0;

    @Override

    /**
     * Thread runnable responsible for invoking the methods in the class
     * <code>ServerProtocol</code> Also prints in the output the string returned
     * from it
     */
    public void run() {
        System.out.println("New thread made");
        while (connection) {
            try {
                String[] uno = new String[20];
                state = 0;
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine, outputLine = null;
                ServerProtocol kappa = new ServerProtocol();

                if ((inputLine = in.readLine()) != null) {
                    uno = kappa.getData(inputLine, socket, chat);
                    System.out.println("inputline " + inputLine);
                } else {
                    uno[0] = "random";
                }
                System.out.println(uno[0]);
                if (uno[0].equals("Login")) {
                    state = 1;
                } else if (uno[0].equals("Signup")) {
                    state = 2;
                } else if (uno[0].equals("ForgotPassword")) {
                    state = 3;
                } else if (uno[0].equals("CreateGame")) {
                    state = 4;
                } else if (uno[0].equals("JoinGame")) {
                    state = 5;
                } else if (uno[0].equals("random")) {
                    state = 0;
                } else if (uno[0].equals("Logout")) {
                    state = 6;
                }else if(uno[0].equals("ChangeProgile")){
                    state=9;
                } else {
                    state = 8;
                }

                switch (state) {
                    case 1:
                        if (uno[1].equals("FailedConnection")) {
                            if (uno[2].equals("USERNAME_FAILED")) {
                                outputLine = "Login&Erro&Username";
                                break;
                            } else if (uno[2].equals("PASSWORD_FAILED")) {
                                outputLine = "Login&Erro&Password";
                                break;
                            }
                        } else {
                            outputLine = "Login&OK&" + uno[1];
                            break;
                        }
                    case 2:
                        if (uno[1].equals("FailedConnection")) {
                            if (uno[2].equals("USERNAME_FAILED")) {
                                outputLine = "Signup&Erro&Username";
                                break;
                            } else if (uno[2].equals("EMAIL_FAILED")) {
                                outputLine = "Signup&Erro&Email";
                                break;
                            }
                        } else {
                            outputLine = "Signup&OK&" + uno[1];
                            break;
                        }
                    case 3:
                        if (uno[1].equals("FailedConnection")) {
                            if (uno[2].equals("EMAIL_FAILED")) {
                                outputLine = "ForgotPassword&Erro&Email";
                                break;
                            } else if (uno[2].equals("USERNAME_FAILED")) {
                                outputLine = "ForgotPassword&Erro&Username";
                                break;
                            } else if (uno[2].equals("PASSWORD_FAILED")) {
                                outputLine = "ForgotPassword&Erro&Password";
                                break;
                            } else if (uno[2].equals("FORGOTPASSWORD_FAILED")) {
                                outputLine = "ForgotPassword&Erro&NotCompatible";
                                break;
                            } else if (uno[2].equals("QUESTION_FAILED")) {
                                outputLine = "ForgotPassword&Erro&Question";
                            } else if (uno[2].equals("ANSWER_FAILED")) {
                                outputLine = "ForgotPassword&Erro&Answer";
                            }

                        } else {
                            outputLine = "ForgotPassword&OK&" + uno[1];
                            break;
                        }
                        
                    case 9:
                        if(uno[1].equals("FailedConnection")){
                            if(uno[2].equals("EMAIL_FAILED")){
                               outputLine = "ChangeProfile&Erro&Email" ;
                               break;
                            }else if(uno[2].equals("NAME_FAILED")){
                                outputLine = "ChangeProfile&Erro&Name";
                                break;
                            }else if(uno[2].equals("USERNAME_FALED")){
                                outputLine = "ChangeProfile&Erro&Username";
                                break;
                            }else if (uno[2].equals("PASSWORD_FAILED")){
                                 outputLine = "ChangeProfile&Erro&Password";
                                 break;
                            }else if(uno[2].equals("CONFIRMPASSWORD_FAILED")){
                                outputLine = "ChangeProfile&Erro&ConfimPassword";
                                 break;
                            }
                        } else {
                            outputLine = "ChangeProfile&OK&" + uno[1];
                            break;
                        }
                        
                        
                    case 4:
                        outputLine = "CreateGame&" + uno[1] + "&" + uno[2];
                        int id = Integer.parseInt(uno[2]);
                        break;
                    case 5:
                        outputLine = "JoinGame&" + uno[1] + "&" + uno[2] + "&" + uno[3];
                        id = Integer.parseInt(uno[1]);
                        break;
                    case 6:
                        outputLine = "Opponent&" + uno[1];
                        break;
                    case 8:
                        outputLine = uno[0];
                        break;

                }
                System.out.println("outputLine " + outputLine);
                out.println(outputLine);
                if (state == 2 || state == 3 || state == 6) {
                    out.close();
                    in.close();
                    socket.close();
                } else {
                    in = null;
                    out = null;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                connection = false;
            } catch (Exception ex) {
                Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
                connection = false;
            }

            if (connection == false) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
