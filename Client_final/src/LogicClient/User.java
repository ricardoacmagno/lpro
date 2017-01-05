package LogicClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ClientCommunication.ClientProtocol;
import GUI.GameUI;
import static GUI.GameUI.gameui;
import GUI.SpectatorUI;
import GUI.UIinicial;
import static GUI.UIinicial.user;
import static LogicClient.Player.client;

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

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
    private String Question = null;
    private String Answer = null;
    private String OldPassword = null;
    private String ConfirmPassword = null;
    private int resultadoLogin = -1;
    private int resultadoPassword = -1;
    private int resultadoChangeProfile = -1;
    public static Game game;
    public static ClientProtocol client;
    public final Lock lock = new ReentrantLock();
    public final Condition notFull = lock.newCondition();
    public final Condition join = lock.newCondition();
    SpectatorUI specui;
    GameUI gameui;
    public static Player player;
    UIinicial ui;

    /**
     * Constructor
     *
     * @param Username string with the user username
     * @param Password string wiith the user password
     * @param Mail string with the user mail
     * @param Name string with the user name
     * @param OldPassword string with the old password
     */
    public User(String Username, String Password, String Mail, String Name, String OldPassword, String Question, String Answer, UIinicial ui) {
        this.Username = Username;
        this.Password = Password;
        this.Mail = Mail;
        this.Name = Name;
        this.OldPassword = OldPassword;
        this.Question = Question;
        this.Answer = Answer;

        this.ui = ui;
        client = new ClientProtocol();
    }
/**
 * Constructor
 * @param email
 * @param name
 * @param username
 * @param password
 * @param confirmPassword
 * @param object
 * @param object0
 * @param aThis 
 */
    public User(JLabel email, String name, String username, String password, String OldPassword, Object object, Object object0, UIinicial aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        sleep(50);
        System.out.println("mail:" + Mail + " Username : " + Username + " Pass: " + Password + " confirmPassword:" + OldPassword + " question" + Question + " answer" + Answer);

        if (ack.equals("Login")) {
            client.sendLogin(Username, Password,ui);
        } else if (ack.equals("Signup")) {
            client.sendSignUp(Name, Mail, Username, Password, Question, Answer,ui);
        } else if (ack.equals("ForgotPassword")) {
            client.sendChangePassword(Mail, Username, Password, OldPassword, Question, Answer, ui);
        } else if (ack.equals("ChangeProfile")) {
            client.sendChangeProfile(Mail,Name, Username, Password, ConfirmPassword, ui);
        }

        try {
            ArrayList<String> dataReceived = null;

            dataReceived = client.hear();
            if (dataReceived != null) {
                if ("Login".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {
                        if ("Username".equals(dataReceived.get(2))) {
                            resultadoLogin = 2;

                        } else if ("Password".equals(dataReceived.get(2))) {
                            resultadoLogin = 3;

                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoLogin = 1;

                        }
                    }
                    client.startListen(this);
                    client.send("GameList");
                } else if ("Signup".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {
                        if ("Username".equals(dataReceived.get(2))) {
                            resultadoLogin = 4;

                        } else if ("Email".equals(dataReceived.get(2))) {
                            resultadoLogin = 5;

                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoLogin = 1;

                        }
                    }
                    client.disconnect();
                } else if ("ForgotPassword".equals(dataReceived.get(0))) {
                    if ("Erro".equals(dataReceived.get(1))) {

                        if ("Email".equals(dataReceived.get(2))) {
                            System.out.println("data received = " + dataReceived.get(2));
                            resultadoPassword = 2;

                        } else if ("Username".equals(dataReceived.get(2))) {
                            resultadoPassword = 3;

                        } else if ("Password".equals(dataReceived.get(2))) {
                            resultadoPassword = 4;

                        } else if ("Question".equals(dataReceived.get(2))) {
                            resultadoPassword = 5;
                        } else if ("Answer".equals(dataReceived.get(2))) {
                            resultadoPassword = 6;
                        } else if ("NotCompatible".equals(dataReceived.get(2))) {
                            resultadoPassword = 7;
                        }
                    } else if ("OK".equals(dataReceived.get(1))) {
                        if (Username.equals(dataReceived.get(2))) {
                            resultadoPassword = 1;

                        }
                    }
                    client.disconnect();
                } else if ("ChangeProfile".equals(dataReceived.get(0))){
                    if("Erro".equals(dataReceived.get(1))){
                        if("Email".equals(dataReceived.get(2))){
                            System.out.println("data received = " + dataReceived.get(2));
                            resultadoChangeProfile = 1;
                        }else if("Password".equals(dataReceived.get(2))){
                            resultadoChangeProfile = 2;
                        }else if("ConfirmPassword".equals(dataReceived.get(2))){
                            resultadoChangeProfile =3;
                        } 
                    }else if("OK".equals(dataReceived.get(1))){
                        if(Name.equals(dataReceived.get(2)) && Username.equals(dataReceived.get(2))){
                            resultadoChangeProfile =4;
                        }
                    }
                }

            }

        } catch (IOException | InterruptedException e) {
            try {
                System.out.println("Disconected");
                client.disconnect();
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
        }
    }

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
     * Method that returns the username
     * @return player's username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * Currently doing nothing
     * @param player 
     */
    public static void setPlayer(Player player) {
        //this.player=player;
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
     * Method to get the result of the change profile
     * @return the result of the change profile
     */
    public int getResultadoChangeProfile(){
        return resultadoChangeProfile;
    }
    

    /**
     * Method that creates a game if there isn't any game or join one
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void getGame() throws IOException, InterruptedException {
        sendtoServer("create");

    }

    /**
     * Method that checks if the game created by the user has an opponent
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void JoinGame(String opponent) throws IOException, InterruptedException {
        client.JoinGame(Username, opponent);
    }

    /**
     * Method used to get the name of the opponent
     *
     * @return a string with the name of the opponent player
     */
    public Game getGamepls() {
        return game;
    }

    /**
     * Method that returns the opponent's name
     * @return opponent's name 
     */
    public String getGameOpponent() {
        return game.getOpponent();
    }

    /**
     * Method that returns <code>true</code> when there's an opponent
     * @return <code>boolean</code>
     */
    public boolean getOpponentBool() {
        return game.getOpponentBoolean();
    }

    /**
     * Method to get the client object
     * @return the client object
     */
    public ClientProtocol getClient() {
        return client;
    }

    /**
     * Method to cancel the current game
     */
    public void cancelGame() {
        client.send("Cancel&" + game.getId() + "&" + game.getName());
    }

    /**
     * Method to refresh data
     * @param dataReceived
     * @throws IOException
     * @throws InterruptedException 
     */
    public void refreshData(String[] dataReceived) throws IOException, InterruptedException {
        if ("CreateGame".equals(dataReceived[0])) {
            System.out.println("I am " + dataReceived[1] + " playing in game id " + dataReceived[2]);
            int gameid = Integer.parseInt(dataReceived[2]);
            game = new Game(gameid, Username);

        } else if ("JoinGame".equals(dataReceived[0])) {

            if (dataReceived[3].equals("ok")) {
                int gameid = Integer.parseInt(dataReceived[1]);
                game = new Game(gameid, Username);
                game.setOpponent(dataReceived[2]);

                ui.setWelcome2(ui.getUsername() + " vs " + user.getGameOpponent());
                gameui = new GameUI(ui.getUsername(), user.getGameOpponent());
                gameui.setVisible(true);
            }

            System.out.println("New opponent " + dataReceived[1]);

        } else if ("Warning".equals(dataReceived[0])) {

            game.setOpponent(dataReceived[2]);

            gameui = new GameUI(ui.getUsername(), user.getGameOpponent());
            gameui.setVisible(true);

        } else if ("Ships".equals(dataReceived[0])) {
            System.out.println("Ships received");
            game.player2placed = true;

            for (int c = 1; c <= 5; c++) {
                String shipinfo = dataReceived[c];
                int y = shipinfo.charAt(0) - '0';
                int x = shipinfo.charAt(1) - '0';
                int size = shipinfo.charAt(2) - '0';
                boolean hor = true;
                System.out.println("y=" + y + " ,x=" + x + " ,size=" + size + " ,hor=" + hor);
                if (shipinfo.charAt(3) == 'V') {
                    hor = false;
                }
                gameui.player1.placeHitBoard(y, x, size, hor);
            }
            int play = Integer.parseInt(dataReceived[6]);
            gameui.setLabel("Opponent turn to play");
            if (play == 1) {
                gameui.player1.setfirstplay();
                gameui.setLabel("Your turn to play");
            }

            if (game.player1placed == true) {
                System.out.println("Grid 2 init");

                gameui.initGrid2();
            }

        } else if ("Turn".equals(dataReceived[0])) {

            String hitinfo = dataReceived[1];
            int y = hitinfo.charAt(0) - '0';
            int x = hitinfo.charAt(1) - '0';
            if (dataReceived[2].equals("hit")) {
                gameui.hitPanel(y, x);
            } else {
                gameui.missPanel(y, x);
                gameui.setLabel("Your turn to play");
                gameui.turn(gameui.player1);
            }
        } else if ("Winner".equals(dataReceived[0])) {
            gameui.setOption("Congrats! You won!");
            gameui.setVisible(false);
            gameui.dispose();
            ui.getIntro();
        } else if ("Loser".equals(dataReceived[0])) {
            gameui.setOption("You lost!");
            gameui.setVisible(false);
            gameui.dispose();
            ui.getIntro();
        } else if ("Chat".equals(dataReceived[0])) {
            ui.RefreshChat(dataReceived[1]);
        } else if ("privateChat".equals(dataReceived[0])) {
            gameui.RefreshChat(dataReceived[1]);
        } else if ("GameAdd".equals(dataReceived[0])) {
            ui.addjList1(dataReceived[1]);
        } else if ("GameRmv".equals(dataReceived[0])) {
            ui.rmvjList1(dataReceived[1]);
        } else if ("GuestLogin".equals(dataReceived[0])) {
            this.Username = dataReceived[1];
        } else if ("Spec".equals(dataReceived[0])) {
            char Separator = ((char) 007);
            int c = 1;
            while (!dataReceived[c].equals("end")) {
                String[] player = dataReceived[c].split(Separator + "");
                if (!player[1].equals("null")) {
                    ui.addjList2(player[0], player[1]);
                    c++;
                }
            }
        } else if ("Spectator".equals(dataReceived[0])) {
            if (dataReceived[1].equals("Ships")) {
                String carrier = dataReceived[6];
                String battleship = dataReceived[5];
                String cruiser = dataReceived[4];
                String submarine = dataReceived[3];
                String destroyer = dataReceived[2];
                String player = dataReceived[7];
                specui.setShips(player, destroyer, submarine, cruiser, battleship, carrier);
            } else if (dataReceived[1].equals("Turn")) {
                specui.updateTurn(dataReceived[2], dataReceived[3], dataReceived[4]);

            } else if (dataReceived[1].equals("privateChat")) {
                specui.updateChat(dataReceived[2]);

            } else if (dataReceived[1].equals("Finish")) {
                specui.setWinner(dataReceived[2]);

            }
        } else if ("SpecAdd".equals(dataReceived[0])) {
            ui.addjList2(dataReceived[1], dataReceived[2]);
        } else if ("SpecRmv".equals(dataReceived[0])) {
            ui.rmvjList2(dataReceived[1], dataReceived[2]);
        } else if ("Rankings".equals(dataReceived[0])) {
            char Separator = ((char) 007);
            ArrayList<Ranks> mysort = new ArrayList<Ranks>();
            int size = dataReceived.length;
            String[] myranks = new String[size - 1];
            int j = 0;
            for (int c = 0; c < size; c++) {
                if (!dataReceived[c].equals("Rankings")) {
                    myranks[j] = dataReceived[c];
                    String[] toadd = myranks[j].split(Separator + "");
                    int wins = Integer.parseInt(toadd[0]);
                    int hits = Integer.parseInt(toadd[1]);
                    int gameshosted = Integer.parseInt(toadd[2]);
                    int gamesjoined = Integer.parseInt(toadd[3]);
                    int losses = Integer.parseInt(toadd[4]);
                    String nick = toadd[5];
                    mysort.add(new Ranks(nick, wins, hits, gameshosted, gamesjoined, losses));
                    j++;
                }
            }

            /*WIns primary, hits secondary*/
            Collections.sort(mysort, new Comparator<Ranks>() {
                public int compare(Ranks o1, Ranks o2) {
                    if (o1.getHits() == o2.getHits()) {
                        return 0;
                    }
                    return o1.getHits() < o2.getHits() ? -1 : 1;
                }
            });
            Collections.sort(mysort, new Comparator<Ranks>() {
                public int compare(Ranks o1, Ranks o2) {
                    if (o1.getWins() == o2.getWins()) {
                        return 0;
                    }
                    return o1.getWins() < o2.getWins() ? -1 : 1;
                }
            });
            Collections.reverse(mysort);
            ui.updateTable(mysort);
        }

    }

    /**
     * Method that sets the UI of the game
     * @param gameui 
     */
    public void set(GameUI gameui) {
        this.gameui = gameui;
    }

    /**
     * Method that calls the method responsible for creating a game
     * @param ack serves as a flag to create a game
     */
    public void sendtoServer(String ack) {
        if (ack.equals("create")) {
            client.CreateGame(Username);
        }
    }

    /**
     * Method that receives a user's message and calls the method that sends the message and the user's username to the server
     * @param user user's username
     * @param tosend chat message
     */
    public void sendChat(String user, String tosend) {
        client.send("Chat&" + user + "&" + tosend);
    }

    /**
     * Method that receives a user's private message and calls the method that sends the message and the user's username to the server
     * @param user user's username
     * @param tosend private message
     */
    public void sendprivateChat(String user, String tosend) {
        client.send("privateChat&" + game.getId() + "&" + game.getMyName() + "&" + tosend);
    }

    /**
     * Method responsible for calling the method responsible for sending messages to the server asking for the rankings
     */
    public void getRanks() {
        client.send("Rankings");
    }

    /**
     * Method that turns on or off the adds
     * @param string
     * @return 
     */
    public boolean turnAdd(String string) {
        return game.turnAdd(string);
    }

    /**
     * Method responsible for asking the server to send a list of the current games being played
     */
    public void getList() {
        client.send("GameList");
    }

    /**
     * Method to listen
     */
    public void Listen() {
        client.connection();
        client.startListen(this);
        client.send("Guest");
    }

    /**
     * Method responsible for calling the method <code>JoinGame</code>
     * @param opponent  opponent's username
     * @throws IOException
     * @throws InterruptedException 
     */
    public void GuestJoinGame(String opponent) throws IOException, InterruptedException {
        client.JoinGame(Username, opponent);
    }

    /**
     * Method that returns the user's username
     * @return user's username
     */
    public String getName() {
        return Username;
    }

    /**
     * Method that retuns the user's name
     * @return user's real name
     */
    public String getrealName() {
        return Name;
    }

    /**
     * Constructor for user's real name
     * @param name 
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Method responsible for calling the <code>send</code> asking for a logout
     */
    public void sendLogout() {
        client.send("Logout");
    }

    /**
     * Method responsible for calling the <code>send</code> asking for a list of games being played
     */
    public void getRunningGames() {
        client.send("RunningGames");
    }

    /**
     * Method responsible for calling the <code>send</code> for sending the server a spectate message with the parameters
     * @param player1   player1's username
     * @param player2   player2's username
     * @param ui 
     */
    public void SpectateGame(String player1, String player2, SpectatorUI ui) {
        this.specui = ui;
        client.send("Spectate&" + player1 + "&" + player2);
    }

    /**
     * Method that informs the server that the user is no longer spectating the game
     * @param player1
     * @param player2 
     */
    public void exitSpec(String player1, String player2) {
        specui.dispose();
        client.send("exitSpec&" + player1 + "&" + player2);
    }
}
