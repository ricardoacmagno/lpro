package ServerCommunication;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static LogicServer.User.gameid;

/**
 * class that represents the thread used by all user requests Responsible for
 * handling calls made to the server by each user
 *
 * @author ricar
 */
public class MultiServerThread extends Thread {

    private Socket socket = null;

    /**
     * Method responsible for handling the multiple calls to the server
     *
     * @param socket Socket id
     */
    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
    String[] uno = new String[5];
    int state = 0;

    @Override

    /**
     * Thread runnable responsible for invoking the methods in the class
     * <code>ServerProtocol</code> Also prints in the output the string returned
     * from it
     */
    public void run() {
        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine = null;
            ServerProtocol kappa = new ServerProtocol();

            if ((inputLine = in.readLine()) != null) {
                uno = kappa.getData(inputLine, socket);
                System.out.println("inputline" + inputLine);
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
            } else if (uno[0].equals("destroyer")) {
                state = 7;
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
                        }

                    } else {
                        outputLine = "ForgotPassword&OK&" + uno[1];
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
                    

            }
            System.out.println(outputLine);
            out.println(outputLine);
            if (state >= 1 && state <=3) {
                out.close();
                in.close();
                socket.close();
                System.out.println("Socket closed");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
