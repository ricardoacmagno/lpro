package ServerCommunication;

import LogicServer.Chat;
import LogicServer.Game;
import LogicServer.User;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Class responsible for handling the string passed as argument from
 * <code>MultiServerThread</code> This class invokes the <code>User</code> class
 * to verify the arguments written by the application user
 *
 * @author ilia&magno
 */
public class ServerProtocol extends Thread {

    Socket mysocket;
    public static Chat chat;

    /**
     * Method responsible for calling the proper method to handle the server
     * call based on the first argument of the <code>String</code> server
     *
     * @param server encoded <code>String</code> with all the information
     * necessary
     * @param mysocket
     * @param chat
     * @return result of the specific method call or null in case of error
     * @throws IOException
     * @throws Exception
     */
    public String[] getData(String server, Socket mysocket, Chat chat) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
        String[] stringUis;
        this.mysocket = mysocket;
        this.chat = chat;
        stringUis = server.split("&");
        switch (stringUis[0]) {
            case "Login":
                chat.newConnection(mysocket);
                return handlerLogin(stringUis);
            case "Signup":
                return handlerSignup(stringUis);
            case "ForgotPassword":
                return handlerForgotPassword(stringUis);
            case "CreateGame":
                this.mysocket = mysocket;
                return handlerCreateGame(stringUis[1]);
            case "JoinGame":
                this.mysocket = mysocket;
                return handlerJoinGame(stringUis[1], stringUis[2]);
            case "Ships":
                return setCarrierInfo(stringUis[1], stringUis[2], stringUis[3], stringUis[4], stringUis[5], stringUis[6], stringUis[7]);
            case "Turn":
                handlerTurn(stringUis[1], stringUis[2], stringUis[3], stringUis[4]);
                String[] ok = {"turn ok"};
                return ok;
            case "Chat":
                chat.newChat(stringUis[1], stringUis[2]);
                String[] ok1 = {"Your chat was sent to all players"};
                return ok1;
            case "privateChat":
                newprivateChat(stringUis[1], stringUis[2], stringUis[3]);
                String[] ok2 = {"Your private chat was sent"};
                return ok2;
            case "Cancel":
                cancelGame(stringUis[1], stringUis[2]);
                String[] ok3 = {"Your game was canceled"};
                return ok3;
            case "GameList":
                User.sendGames(mysocket, chat);
                String[] ok4 = {"Sent game list"};
                return ok4;
            case "Rankings":
                User.getRankings(mysocket);
                String[] ok5 = {"Sent ranks list"};
                return ok5;
            case "Guest":
                loginGuest(mysocket);
                String[] ok6 = {"Guest player joined"};
                return ok6;
            case "Logout":
                chat.rmvConnection(mysocket);
                String[] logout = {"Trying to logout and close connections"};
                return logout;
            case "RunningGames":
                chat.SendGames(mysocket);
                String[] sendgames = {"Trying to send games to spectator"};
                return sendgames;
            case "Spectate":
                User.Spectate(stringUis[1], stringUis[2], mysocket);
                String[] specgames = {"Trying to allow spectator"};
                return specgames;
            case "exitSpec":
                User.exitSpec(stringUis[1], stringUis[2], mysocket);
                String[] specexit = {"Exiting spectator"};
                return specexit;
            case "ChangeProfile":
                return handlerChangeProfile(stringUis);
            default:
                return null;
        }

    }

    /**
     * Method that verifies the parameters inserted by the user in the login
     * using the <code>User</code> class
     *
     * @param receive   <code>String</code> array with the encoded information
     * necessary to start the server login protocol
     * @return Vector of <code>String</code> with the parameters inserted by the
     * user if these are verified or error message in case of failure
     * @throws Exception
     */
    public String[] handlerLogin(String[] receive) throws Exception {
        int state = 0;
        String[] UsernameEPass = new String[3];
        for (String Ui : receive) {
            //System.out.println(Ui);
            if (state == 0) {
                if (Ui.equals("Login")) {
                    UsernameEPass[0] = Ui;
                    state = 1;
                } else {
                    return new String[]{"Login", "FailedConnection", "LOGIN_FAILED"};
                }
            } else if (state == 1) {
                if (User.confirmUsername(Ui, chat)) {              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                    UsernameEPass[1] = Ui;
                    System.out.println("Protocol_Username: " + Ui);
                    state = 2;
                } else {
                    System.out.println("USERNAME_NOT_FOUND: " + Ui);      //VERIFICAR ISTO
                    return new String[]{"Login", "FailedConnection", "USERNAME_FAILED"};
                }
            } else if (state == 2) {
                if (User.confirmPassword(Ui)) {               //SAME AS ABOVE
                    UsernameEPass[2] = Ui;
                    System.out.println("DataBase_Hash: " + Ui);
                    System.out.println("LOGIN_SUCCESFULL");
                    return UsernameEPass;
                }
                System.out.println("WRONG_PASSWORD");
                return new String[]{"Login", "FailedConnection", "PASSWORD_FAILED"};
            }
        }
        return new String[]{"FailedConnection", "login"};
    }

    /**
     * Method that verifies if the parameters inserted by the user in the signup
     * are already beeing used, invoking the <code>User</code> class
     *
     * @param receive   <code>String</code> array with the encoded information
     * necessary to start the server signup protocol
     * @return Vector of <code>String</code> with the parameters inserted by the
     * user if these are verified or error message in case of failure
     * @throws IOException
     * @throws Exception
     */
    public String[] handlerSignup(String[] receive) throws IOException, Exception {                  // DONE FOR NOW // CREATE PLAYER CLASS
        String[] Data = new String[3];
        int state = 0;
        for (String Ui : receive) {

            if (state == 0) {
                if (Ui.equals("Signup")) {
                    Data[0] = Ui;
                    state = 1;
                } else {
                    System.out.println("SIGNUP_FAILED");
                    return new String[]{"Signup", "FailedConnection", "SIGNUP_FAILED"};
                }
            } else if (state == 1) {
                if (!User.confirmUsername(Ui, chat)) {              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                    Data[1] = Ui;
                    System.out.println("DataBase_Username: " + Ui);
                    state = 2;
                } else {
                    System.out.println("USERNAME_ALREADY_USED: " + Ui);      //VERIFICAR ISTO
                    return new String[]{"Signup", "FailedConnection", "USERNAME_FAILED"};
                }
            } else if (state == 2) {
                if (!User.confirmEmail(Ui)) {               //SAME AS ABOVE
                    Data[2] = Ui;
                    System.out.println("DataBase_Email: " + Ui);
                    state = 3;
                } else {
                    System.out.println("EMAIL_ALREADY_USED: " + Ui);
                    return new String[]{"Signup", "FailedConnection", "EMAIL_FAILED"};
                }
            } else if (state == 3) {
                //Chamar a função de escrita na base de dados através do User
                User.sendSignup(receive);
                System.out.println("SIGNUP_SUCCESFULL");
                return Data;
            }
        }
        return new String[]{"FailedConnection", "signup"};
    }

    /**
     * Method that verifies the parameters inserted by the user in the change
     * password call using the <code>User</code> class
     *
     * @param receive   <code>String</code> array with the encoded information
     * necessary to start the server password change protocol
     * @return Vector of <code>String</code> with the parameters inserted by the
     * user if these are verified or error message in case of failure
     * @throws Exception
     */
    private String[] handlerForgotPassword(String[] receive) throws Exception {
        String[] ChangePass = new String[7];
        int state = 0;
        for (String Ui : receive) {

            System.out.println("RECEBIDO : " + Ui);
            if (state == 0) {
                if (Ui.equals("ForgotPassword")) {

                    ChangePass[0] = Ui;
                    state = 1;
                } else {
                    System.out.println(" FORGOT_PASSWORD FAILED! ");
                    return new String[]{"ForgotPassword", "FailedConnection", "FORGOTPASSWORD_FAILED"};
                }
            } else if (state == 1) {
                if (User.confirmEmailForPassword(Ui)) {

                    System.out.println("user confirmEmail" + User.confirmEmail(Ui));
                    ChangePass[1] = Ui;
                    System.out.println("DataBase_Email: " + Ui);
                    state = 2;

                } else {
                    System.out.println("EMAIL_NOT_FOUND " + Ui);      //VERIFICAR ISTO
                    return new String[]{"ForgotPassword", "FailedConnection", "EMAIL_FAILED"};
                }
            } else if (state == 2) {
                if (User.confirmUsername(Ui, chat)) {              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                    System.out.println("CONFIRM USERNAME FUNCIONA");
                    ChangePass[2] = Ui;
                    System.out.println("Protocol_Username: " + Ui);
                    state = 3;

                } else {
                    System.out.println("USERNAME_NOT_FOUND: " + Ui);      //VERIFICAR ISTO
                    return new String[]{"ForgotPassword", "FailedConnection", "USERNAME_FAILED"};
                }
            } else if (state == 3) {

                if (User.confirmPassword(Ui)) {              //CRIAR NA CLASSE PLAYER O METODO CONFIRMUSERNAME
                    ChangePass[3] = Ui;
                    System.out.println("Protocol_Password: " + Ui);
                    state = 4;
                } else {
                    System.out.println("PASSWORD_NOT_FOUND: " + Ui);      //VERIFICAR ISTO
                    return new String[]{"ForgotPassword", "FailedConnection", "PASSWORD_FAILED"};
                }

            } else if (state == 4) {

                if (User.confirmQuestion(Ui)) {
                    ChangePass[4] = Ui;
                    System.out.println("Protocol_Question: " + Ui);
                    state = 5;
                } else {
                    System.out.println("QUESTION_NOT_FOUND: " + Ui);
                    return new String[]{"ForgotPassword", "FailedConnection", "QUESTION_FAILED"};
                }
            } else if (state == 5) {
                if (User.confirmAnswer(Ui)) {
                    ChangePass[5] = Ui;
                    System.out.println("Protocol_Answer: " + Ui);
                    state = 6;
                } else {
                    System.out.println("ANSWER_NOT_FOUND: " + Ui);
                    return new String[]{"ForgotPassword", "FailedConnection", "ANSWER_FAILED"};
                }

            } else if (state == 6) {

                if (User.sendForgotPassword(receive) > 0) {
                    System.out.println("PASSWORD_SUCCESFULL");
                    return ChangePass;
                } else {
                    System.out.println("PASSWORD_FAILED");
                    return new String[]{"ForgotPassword", "FailedConnection", "FORGOTPASSWORD_FAILED"};
                }

            }

        }
        return new String[]{"FailedConnection", "forgotPassword"};
    }

    /**
     * Method to handle the change profile
     * @param receive
     * @return info with the changes 
     * @throws Exception 
     */
    private String[] handlerChangeProfile(String[] receive) throws Exception {

        String[] ChangeProf = new String[7];
        int state = 0;
        for (String Ui : receive) {
            if (state == 0) {
                if (Ui.equals("ChangeProfile")) {

                    ChangeProf[0] = Ui;
                    state = 1;
                } else {
                    System.out.println(" CHANGE_PROFILE FAILED! ");
                    return new String[]{"ChangeProfile", "FailedConnection", "FORGOTPASSWORD_FAILED"};
                }
            } else if (state == 1) {

                if (User.confirmEmail(Ui)) {               //SAME AS ABOVE
                    ChangeProf[1] = Ui;
                    System.out.println("DataBase_Email: " + Ui);
                    state = 2;
                } else {
                    System.out.println(" EMAIL FAILED! ");
                    return new String[]{"ChangeProfile", "FailedConnection", "EMAIL_FAILED"};
                }
            } else if (state == 2) {

                if (User.confirmName(Ui)) {
                    ChangeProf[2] = Ui;
                    System.out.println("Protocol_name " + Ui);
                    state = 3;
                } else {
                    System.out.println("NAME FAILED! ");
                    return new String[]{"ChangeProfile", "FailedConnection", "NAME_FAILED"};
                }

            } else if (state == 3) {
                if (User.confirmUsername(Ui, chat)) {
                    ChangeProf[3] = Ui;
                    System.out.println("Protocol_Username: " + Ui);
                    state = 4;
                } else {
                    System.out.println("USERNAME FAILED!");
                    return new String[]{"ChangeProfile", "FailedConnection", "USERNAME_FAILED"};
                }
            } else if (state == 4) {
                if (User.confirmPassword(Ui)) {               //SAME AS ABOVE
                    ChangeProf[4] = Ui;
                    System.out.println("DataBase_Hash: " + Ui);
                    state = 5;
                } else {
                    System.out.println("PASSWORD FAILED!");
                    return new String[]{"ChangeProfile", "FailedConnection", "PASSWORD_FAILED"};
                }

            } else if (state == 5) {
                if (User.confirmPassword(Ui)) {               //SAME AS ABOVE
                    ChangeProf[5] = Ui;
                    System.out.println("DataBase_Hash: " + Ui);
                    state = 6;
                } else {
                    System.out.println("PASSWORD FAILED!");
                    return new String[]{"ChangeProfile", "FailedConnection", "PASSWORD_FAILED"};

                }
            } else if (state == 6) {
                if (User.sendChangeProfile(receive) > 0) {
                    System.out.println("CHANGEPROFILE_SUCCESFULL");
                    return ChangeProf;
                } else {
                    System.out.println("PASSWORD_FAILED");
                    return new String[]{"ChangeProfile", "FailedConnection", "CHANGEPROFILE_FAILED"};
                }
            }

        }

        return new String[]{"FailedConnection", "ChangeProfile"};
    }

    /**
     *Method to handler a create a game
     * @param user
     * @return the info with the game created
     * @throws Exception
     */
    public String[] handlerCreateGame(String user) throws Exception {
        System.out.println("CreateGameHandler received " + user);
        String[] returned = User.UserCreateGame(user, mysocket, chat);
        int id = Integer.parseInt(returned[1]);
        User.setSocketPlayer1(mysocket, id);
        chat.newGame(user);
        String[] toReturn = {"CreateGame", returned[0], returned[1]};
        System.out.println("Server created game");
        return toReturn;
    }

    /**
     *Method to handler a join game
     * @param id
     * @return the info with the join game
     */
    public String[] handlerJoinGame(String user, String stringopponent) throws SQLException, IOException, InterruptedException {
        System.out.println("Sending JoinGame to logic server");
        String[] opponent = User.JoinGame(user, stringopponent);
        int id = Integer.parseInt(opponent[0]);
        User.setSocketPlayer2(mysocket, id);
        chat.rmvGame(stringopponent);
        User.sendWarning(id);
        sleep(50);
        String[] teste = {"JoinGame", opponent[0], opponent[1], opponent[2]};
        return teste;
    }

    /**
     * Method to set a carrier info
     * @param sid
     * @param infod
     * @param infos
     * @param infoc
     * @param infob
     * @param infoca
     * @param username
     * @return  info with the carrier
     */
    public String[] setCarrierInfo(String sid, String infod, String infos, String infoc, String infob, String infoca, String username) {
        System.out.println("Setting ships info");
        int id = Integer.parseInt(sid);
        Game game = User.getGameid(id);
        String tosend = infod + "&" + infos + "&" + infoc + "&" + infob + "&" + infoca;
        String ok = game.setShipsInfo(tosend, username);
        String[] toreturn = {ok};
        return toreturn;
    }

    /**
     * Method to handler a turn
     * @param sid
     * @param position
     * @param result
     * @param myname
     * @throws IOException
     * @throws SQLException
     * @throws InterruptedException 
     */
    public void handlerTurn(String sid, String position, String result, String myname) throws IOException, SQLException, InterruptedException {
        int id = Integer.parseInt(sid);
        Game game = User.getGameid(id);
        game.setTurn(result, position, myname);
        if (game.getWinnerbool()) {
            User.finishGame(game);
        }
    }

    /**
     * Method to create a new private chat 
     * @param sid
     * @param username
     * @param received 
     */
    public void newprivateChat(String sid, String username, String received) {
        int id = Integer.parseInt(sid);
        Game game = User.getGameid(id);
        game.newPrivateChat(username, received);
    }

    /**
     * Method to cancel a game
     * @param sid
     * @param user
     * @throws SQLException 
     */
    public static void cancelGame(String sid, String user) throws SQLException {
        int id = Integer.parseInt(sid);

        User.cancelGame(id);
        chat.rmvGame(user);
    }
    
    /**
     * Method to login has a guest 
     * @param socket
     * @throws IOException 
     */
    public void loginGuest(Socket socket) throws IOException {
        chat.newGuest(socket);
    }

}
