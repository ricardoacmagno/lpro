package ClientCommunication;

import LogicClient.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>ClientProtocol</code> represents the protocol part of the Client-Server
 * connection Responsible for commanding all the communication procedures
 *
 * @author ilia
 */
public class ClientProtocol {

    private boolean connect = false;
    private SocketClient clientSocket;
    private final ConcurrentHashMap<Integer, Object> sleepers = new ConcurrentHashMap<>();

    /**
     * Method responsible for calling the socket communication initialization
     * process
     *
     * @return always <code>true</code>
     */
    public boolean connection() {
        if (connect == false) {
            try {
                System.out.println("Needed to reconnect");
                clientSocket = new SocketClient();
                clientSocket.openSocket();
                connect = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Serves only to check if the Client-Server connection has been established
     *
     * @return <code>true</code> if the communication has been established or
     * <code>false</code> otherwise
     */
    public boolean okConnection() {
        return connect;
    }

    /**
     * Method responsible for calling the socket communication closure process
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if (connect) {
            clientSocket.close();
            connect = false;
        }
    }

    /**
     * Method responsible for creating a specific login encoded string Calls the
     * <code>toSend</code> method to send the message
     *
     * @param username user's login username
     * @param password user's login password
     */
    public void sendLogin(String username, String password) {
        if (connect == false) {
            connection();
        }

        String message = "Login&" + username + "&" + password;
        try {
            clientSocket.toSend(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startListen(User user) {
        new Listen(clientSocket.getSocket(), user).start();
    }

    /**
     * Method responsible for creating a specific signup encoded string Calls
     * the <code>toSend()</code> method to send the message
     *
     * @param name user's real name
     * @param mail user's email address
     * @param username user's unique username
     * @param password user's password
     */
    public void sendSignUp(String name, String mail, String username, String password) {
        if (connect == false) {
            connection();
        }
        String message = "Signup&" + username + "&" + mail + "&" + password + "&" + name;
        try {
            clientSocket.toSend(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method responsible to send the new password of the user
     *
     * @param mail user's email address
     * @param username user's username
     * @param OldPassword user's old password
     * @param NewPassword user's new password
     */
    public void sendChangePassword(String mail, String username, String OldPassword, String NewPassword) {
        if (connect == false) {
            connection();
        }

        String ChangePassword = "ForgotPassword&" + mail + "&" + username + "&" + OldPassword + "&" + NewPassword;

        System.out.println("CHANGEPASSWORD USER " + ChangePassword);
        try {
            clientSocket.toSend(ChangePassword);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to create or join a game
     *
     * @param user string with the name of the user
     */
    public void CreateGame(String user) {
        if (connect == false) {
            connection();
        }
        String createGame = "CreateGame&" + user;
        try {
            clientSocket.toSend(createGame);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to check if the opponent is avaiable
     *
     * @param id
     */
    public void JoinGame(String user) {
        if (connect == false) {
            connection();
        }
        String checkGame = "JoinGame&" + user;
        try {
            clientSocket.toSend(checkGame);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendBoat(String mystring) {

        try {
            clientSocket.toSend(mystring);
        } catch (IOException ex) {
            Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method responsible for checking the server's response Calls the
     * <code>received()</code> to receive the server's message
     *
     * @return  <code>ArrayList</code> with a simplified confirmation code
     * @throws IOException
     * @throws InterruptedException
     */
    public ArrayList<String> hear() throws IOException, InterruptedException {

        //if(connect==false)  connection();
        String echo;
        echo = clientSocket.received();

        if (echo != null) {
            String[] tokens = echo.split("&");

            switch (tokens[0]) {
                case "Login":
                    return handlerLogin(tokens);
                case "Signup":
                    return handlerSignup(tokens);
                case "ForgotPassword":
                    return handlerForgotPassword(tokens);
                case "CreateGame":
                    return handlerCreate(tokens);
                case "JoinGame":
                    return handlerJoinGame(tokens);
                case "Warning":
                    return handlerWarning(tokens);
                case "Ships":
                    return handlerShips(tokens);
                case "destroyer":
                    return handlerok(tokens);
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Creates a specific <code>ArrayList</code> with the server's response
     * split
     *
     * @param tokens Server's confirmation with a <code>String</code> array
     * @return a simplified confirmation code
     */
    private ArrayList<String> handlerLogin(String[] tokens) {          //ATENCAO: Quando enviar confirmação do server, ter cuidado para enviar um vetor de strings com 3 elementos ou modificar o código

        ArrayList<String> login;
        login = new ArrayList<>();
        int j = 0;

        login.add(tokens[j++]);
        login.add(tokens[j++]);
        login.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return login;
    }

    /**
     * Method to handle the opponent
     *
     * @param tokens strings received from server
     * @return a simplified confirmation code
     */
    private ArrayList<String> handlerJoinGame(String[] tokens) {          //ATENCAO: Quando enviar confirmação do server, ter cuidado para enviar um vetor de strings com 3 elementos ou modificar o código

        ArrayList<String> login;
        login = new ArrayList<>();
        int j = 0;

        login.add(tokens[j++]);
        login.add(tokens[j++]);
        login.add(tokens[j++]);
        login.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return login;
    }

    /**
     * Method to handle the game checked
     *
     * @param tokens strings received from server
     * @return a simplified confirmation code
     */
    private ArrayList<String> handlerCreate(String[] tokens) {          //ATENCAO: Quando enviar confirmação do server, ter cuidado para enviar um vetor de strings com 3 elementos ou modificar o código

        ArrayList<String> check;
        check = new ArrayList<>();
        int j = 0;

        check.add(tokens[j++]);
        check.add(tokens[j++]);
        check.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return check;
    }

    /**
     * Creates a specific <code>ArrayList</code> with the server's response
     * split
     *
     * @param tokens strings received from server
     * @return a simplified confirmation code
     */
    private ArrayList<String> handlerSignup(String[] tokens) {

        ArrayList<String> signup;
        signup = new ArrayList<>();
        int j = 0;

        signup.add(tokens[j++]);
        signup.add(tokens[j++]);
        signup.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return signup;
    }

    /**
     * Creates a specific <code>ArrayList</code> with the server's response
     * split
     *
     * @param tokens strings received from server
     * @return a simplified confirmation code
     */
    private ArrayList<String> handlerForgotPassword(String[] tokens) {
        ArrayList<String> forgotpassword;

        forgotpassword = new ArrayList<>();
        int j = 0;
        forgotpassword.add(tokens[j++]);
        forgotpassword.add(tokens[j++]);
        forgotpassword.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return forgotpassword;
    }

    private ArrayList<String> handlerWarning(String[] tokens) {
        ArrayList<String> warning;

        warning = new ArrayList<>();
        int j = 0;
        warning.add(tokens[j++]);
        warning.add(tokens[j++]);
        warning.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return warning;
    }

    private ArrayList<String> handlerShips(String[] tokens) {
        ArrayList<String> warning;

        warning = new ArrayList<>();
        int j = 0;
        warning.add(tokens[j++]);
        warning.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return warning;
    }

    private ArrayList<String> handlerok(String[] tokens) {
        ArrayList<String> warning;

        warning = new ArrayList<>();
        int j = 0;
        warning.add(tokens[j++]);
        warning.add(tokens[j]);
        System.out.println(Arrays.toString(tokens));

        return warning;
    }

}
