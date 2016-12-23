package LogicServer;

import DataBase.UserDB;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Class responsible for handling the connection between the server and the
 * database
 *
 * @author ilia&magno
 */
public class User {

    private String name;
    private String email;
    private String username;
    private String password;
    private Integer question;
    private String anwser;
    public int c;
    public static Game gameid[] = new Game[999999999];
    public static UserDB userData;

    /**
     * Constructor
     *
     * @param name User's real name
     * @param email User's email
     * @param username User's username
     * @param password User's password hash
     * @param question User's security question id
     * @param anwser User's answer for the security question
     * @throws Exception
     */
    public User(String name, String email, String username, String password, Integer question, String anwser) throws Exception {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.question = question;
        this.anwser = anwser;
    }

    // NOT SURE IF NECESSARY FOR NOW //
    /*public User(String username) {
        this.username = username;
    }*/
    /**
     * Gets the name stored in the class <code>User</code>
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email stored in the class <code>User</code>
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password stored in the class <code>User</code>
     *
     * @return user's password hash
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username stored in the class <code>User</code>
     *
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the security question id stored in the class <code>User</code>
     *
     * @return user's security question id
     */
    public Integer getQuestion() {
        return question;
    }

    /**
     * Gets the security question answer stored in the class <code>User</code>
     *
     * @return user's safety question answer
     */
    public String getAnwser() {
        return anwser;
    }

    /**
     * Method that creates a new class <code>UserDB</code> in order to verify
     * the existence of the user's username in the database
     *
     * @param received User's username
     * @return <code>true</code> if the user's username is in the database or
     * <code>false</code> if it is not
     * @throws Exception
     */
    public static boolean confirmUsername(String received) throws Exception {
        System.out.println("USER.RECEIVED: " + received);
        userData = new UserDB();
        userData.setUsername(received);
        userData.getLine();
        //Chamar a parte do DB >> verificar se encontrou uma lista através daquele username

        return received.equals(userData.getUsername());                //SAME AS WHAT FOLLOWS

    }

    /**
     * Method that verifies if the password's hash is correct
     *
     * @param received password's hash
     * @return  <code>true</code> if the password's hash is correct or
     * <code>false</code> in case of error
     */
    public static boolean confirmPassword(String received) {
        System.out.println("HASH: " + received);
        //Chamar a parte do DB >> verificar a palavra passe retornada com aquela recebida  
        return received.equals(userData.getPassword());              //CRIAR O GETPASSWORD NO USERDB PARA RETORNAR A PALAVRA PASSE
    }

    /**
     *
     * @param user
     * @return
     */
    public static String[] UserCreateGame(String user) {
        String[] returned = userData.getGame(user);
        int id = Integer.parseInt(returned[1]);
        gameid[id] = new Game(returned[0], id);
        System.out.println("I have " + returned[0] + " and " + returned[1]);
        return returned;
    }

    /**
     *
     * @param id
     * @return
     */
    public static String[] JoinGame(String user) throws SQLException {
        System.out.println("Sending info to DB");
        String[] opponent = userData.JoinGame(user);
        int id = Integer.parseInt(opponent[0]);
        if ("ok".equals(opponent[2])) {
            gameid[id].setOpponent(user);
        }
        return opponent;
    }

    /**
     * Method that verifies that the email received exists on the database
     *
     * @param received user's email
     * @return  <code>true</code> if the email is in the database or
     * <code>false</code> if it is not
     * @throws Exception
     */
    public static boolean confirmEmailForPassword(String received) throws Exception {

        userData = new UserDB();
        userData.setEmail(received);

        return userData.getEmail(received);
    }

    /**
     * Method that verifies if the email picked by the user is already in use
     *
     * @param received user's received email
     * @return   <code>true</code> if the email is in the database and corresponds
     * to the right user or <code>false</code> if it doesn't
     */
    public static boolean confirmEmail(String received) {           //verifica se existe um email na base
        System.out.println("EMAIL.RECEIVED: " + received);

        return userData.getEmail(received);                //SAME AS WHAT FOLLOWS
    }

    /**
     * Method that uses the <code>UserDB</code> class to insert the signup
     * parameters in the database
     *
     * @param data  <code>String</code> array with the user's data. Username,
     * Email, Password and Name
     */
    public static void sendSignup(String[] data) {
        userData.newLine(data[1], data[3], data[4], data[2]);   //Manda primeiro o Username, Password, Nome e Email
    }

    /**
     * Method that uses the <code>UserDB</code> class to change a user's
     * password
     *
     * @param ChangePassword
     * @return a positive value in case of success or a negative error flag
     */
    public static int sendForgotPassword(String[] ChangePassword) {
        return userData.newPass(ChangePassword[1], ChangePassword[2], ChangePassword[3], ChangePassword[4]);
    }

    public static void setSocketPlayer1(Socket mysocket, int id) throws IOException {
        gameid[id].setSplayer1(mysocket);
    }

    public static void setSocketPlayer2(Socket mysocket, int id) throws IOException {
        gameid[id].setSplayer2(mysocket);
    }

    public static void sendWarning(int id) throws IOException {
        gameid[id].newOpponent();
    }

    public static Game getGameid(int id) {
        return gameid[id];
    }
    public static void finishGame(Game game) throws SQLException{
        userData.finishGame(game);
    }
}
