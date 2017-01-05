package DataBase;

import LogicServer.Game;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for accessing the database, adding values to it and
 * verifying all the database data
 *
 * @author ilia&magno
 */
public final class UserDB extends PostgreSQLink {

    private String username;
    private String password;
    private String email;
    private String question;
    private String answer;
    private String name;

    Statement statement = null;

    /**
     * Constructor
     *
     * @throws Exception
     */
    public UserDB() throws Exception {
    }

    /**
     * Sets the <code>username</code> with the parameters received
     *
     * @param username user's received username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the <code>email</code> with the parameters received
     *
     * @param email user's received email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Mehtod to set the question
     * @param question 
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Method to set the answer
     * @param answer 
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Method to set the name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that sets the <code>password</code> from the database based on a
     * username previously defined in <code>setUsername</code>
     */
    public void getLine() {
        PostgreSQLink.connect();
        try {
            statement = getConnection().createStatement();

            ResultSet results = statement.executeQuery("SELECT password FROM signuplpro WHERE username = '" + this.username + "';");
            if (results.next()) {
                this.password = results.getString("password");
            }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
    }

    /**
     * Gets the user's username of this class, <code>UserDB</code>
     *
     * @return username or, if there's no password attached to the specific
     * username, an error flag
     */
    public String getUsername() {
        if (this.password == null) {
            return "Error&";
        }
        return username;
    }

    /**
     * Gets the user's stored password
     *
     * @return the password previously set on <code>getLine</code>
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to get the question
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Method to get the answer
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Method to get the names
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the game
     * @param user
     * @return the info with the game
     */
    public String[] getGame(String user) {
        int id = 0;
        try {
            PostgreSQLink.connect();
            // Statement statement = getConnection().createStatement();
            statement = getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "
                    + "gamesrunning(player1name, player2name, player1joined, player2joined, shipsplayer1, shipsplayer2, player1ready, player2ready, winner) "
                    + "VALUES ('" + user + "','" + "default" + "','" + true + "','" + false + "','" + null + "','" + null + "','" + false + "','" + false + "','" + null + "');");
            try (ResultSet results5 = statement.executeQuery("SELECT id FROM gamesrunning WHERE player2joined = '" + false + "' AND player1name = '" + user + "';")) {
                if (results5.next()) {

                    id = results5.getInt("id");
                    System.out.println("Game of " + user + " created with id of " + id);
                }
                System.out.println(id);
                results5.close();
            }
            ResultSet results2 = statement.executeQuery("SELECT gameshosted FROM signuplpro WHERE username = '" + user + "';");
            if (results2.next()) {
                int gamescreated = results2.getInt("gameshosted") + 1;
                statement.executeUpdate("UPDATE signuplpro SET gameshosted = '" + gamescreated + "' WHERE username = '" + user + "';");
            }
            statement.close();

        } catch (Exception ex) {
            if (!ex.getMessage().equals("No results were returned by the query.")) {
                System.err.println("Error!" + ex.getMessage());
            }
        }
        String oops = id + "";
        String[] toReturn = {user, oops};
        return toReturn;
    }

    /**
     * Method that verifies if the email received as a parameter exists in the
     * database
     *
     * @param email
     * @return  <code>true</code> if there is such email or <code>false</code> if
     * it doesn't
     */
    public Boolean getEmail(String email) {

        try {
            PostgreSQLink.connect();
            // Statement statement = getConnection().createStatement();
            statement = getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT email FROM signuplpro WHERE email = '" + email + "';");
            if (results.next()) {
                return true;
            }
            statement.close();
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }

        return false;
    }

    /**
     *Method to Join the Game
     * @param id
     * @return the info of the game join
     */
    public String[] JoinGame(String user, String sopponent) throws SQLException {
        System.out.println("Checking game...");
        PostgreSQLink.connect();
        int id = 0;
        String opponent = "";
        String ok = "ok";
        // Statement statement = getConnection().createStatement();
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT id,player1name FROM gamesrunning WHERE player1joined = '" + true + "' AND player2joined = '" + false + "'  AND player1name = '" + sopponent + "';");
        if (results1.next()) {
            opponent = results1.getString("player1name");
            id = results1.getInt("id");
            statement.executeUpdate("UPDATE gamesrunning SET player2joined = '" + true + "', player2name = '" + user + "' WHERE player1joined = '" + true + "'  AND id = '" + id + "';");
            System.out.println("Info of player " + user + " placed in DB, he joined game of " + opponent + " with game id " + id);
            results1.close();
        } else {
            ok = "notok";
        }
        ResultSet results2 = statement.executeQuery("SELECT gamesjoined FROM signuplpro WHERE username = '" + user + "';");
        if (results2.next()) {
            int gamesjoined = results2.getInt("gamesjoined") + 1;
            statement.executeUpdate("UPDATE signuplpro SET gamesjoined = '" + gamesjoined + "' WHERE username = '" + user + "';");
        }
        statement.close();
        String sid = "" + id;
        String[] toReturn = {sid, opponent, ok};

        return toReturn;
    }

    /**
     * Method that creates a new line of information, based on the signup of the
     * user, into the database
     *
     * @param Username User's received username
     * @param Password User's received password
     * @param Name User's received real name
     * @param Mail User's received email
     */
    public void newLine(String Username, String Password, String Name, String Mail, String Question, String Answer) {

        try {
            statement = getConnection().createStatement();
            //System.out.println("statemente ="  + statement);
            statement.executeQuery("INSERT INTO signuplpro(name, email, username, password, question, answer) VALUES ('" + Name + "','" + Mail + "','" + Username + "','" + Password + "','" + Question + "','" + Answer + "');");  //"' DEFAULT");
            statement.close();
        } catch (Exception e) {
            if (!e.getMessage().equals("No results were returned by the query.")) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    /**
     * Method responsible for changing a user's stored password based on the
     * parameters received
     *
     * @param mail User's received email
     * @param Username User's received username
     * @param OldPassword User's received previous password
     * @param Password User's received new password
     * @return an error flag in case of error or a positive <code>int</code> in
     * case of success
     */
    public int newPass(String mail, String Username, String OldPassword, String Question, String Answer, String Password) {
        try {
            PostgreSQLink.connect();
            statement = getConnection().createStatement();
            System.out.println("Mail: "+mail+" Username: "+Username+" OldPassword: "+OldPassword+" Question: "+Question+" Answer: "+Answer+" Pw: "+Password);
            return statement.executeUpdate("UPDATE signuplpro SET password='" + Password + "' WHERE username = '" + Username + "';");
        } catch (Exception e) {

            System.err.println("Error!" + e.getMessage());
            if (!e.getMessage().equals("No results were returned by the query.")) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        return -1;

    }

    /**
     * Method to create a new profile
     * @param mail
     * @param Name
     * @param Username
     * @param Password
     * @param ConfirmPassword
     * @return the new profile
     */
    public int newProfile(String mail, String Name, String Username, String Password, String ConfirmPassword) {

        try {
            PostgreSQLink.connect();
            statement = getConnection().createStatement();

            return statement.executeUpdate("UPDATE signuplpro SET name = '" + Name + "' + username = '" + Username + "' WHERE email ='" + mail + "' and password='" + Password + "' and password ='" + ConfirmPassword + "';");
        } catch (Exception e) {

            System.err.println("Error!" + e.getMessage());
            if (!e.getMessage().equals("No results were returned by the query.")) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return -1;
    }

    /**
     * Method to finish the game
     * @param game
     * @throws SQLException 
     */
    public void finishGame(Game game) throws SQLException {
        PostgreSQLink.connect();
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT shipsplayer1, shipsplayer2 , winner FROM gamesrunning WHERE id = '" + game.getId() + "';");
        if (results1.next()) {
            statement.executeUpdate("UPDATE gamesrunning SET shipsplayer1 = '" + game.getPlayer2Ships() + "', shipsplayer2 = '" + game.getPlayer2Ships() + "', winner = '" + game.getWinner() + "' WHERE id= '" + game.getId() + "';");
        }
        ResultSet results2 = statement.executeQuery("SELECT hits, wins FROM signuplpro WHERE username = '" + game.getWinner() + "';");
        if (results2.next()) {

            int wins = results2.getInt("wins") + 1;
            int hits = results2.getInt("hits") + game.getWinnerHits();
            statement.executeUpdate("UPDATE signuplpro SET hits = '" + hits + "', wins = '" + wins + "' WHERE username = '" + game.getWinner() + "';");

        }
        ResultSet results3 = statement.executeQuery("SELECT hits, losses FROM signuplpro WHERE username = '" + game.getLoser() + "';");
        if (results3.next()) {

            int losses = results3.getInt("losses") + 1;
            int hits = results3.getInt("hits") + game.getLoserHits();
            statement.executeUpdate("UPDATE signuplpro SET hits = '" + hits + "', losses = '" + losses + "' WHERE username = '" + game.getLoser() + "';");

        }
        statement.close();
    }

    /**
     * Method to cancel the game
     * @param id
     * @param user
     * @throws SQLException 
     */
    public void cancelGame(int id, String user) throws SQLException {
        PostgreSQLink.connect();
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT player2joined, winner FROM gamesrunning WHERE id = '" + id + "';");
        if (results1.next()) {
            statement.executeUpdate("UPDATE gamesrunning SET player2joined = '" + true + "', winner = '" + "canceled" + "' WHERE id= '" + id + "';");
        }
        ResultSet results2 = statement.executeQuery("SELECT gameshosted FROM signuplpro WHERE username = '" + user + "';");
        if (results2.next()) {

            int gamescreated = results2.getInt("gameshosted") - 1;
            statement.executeUpdate("UPDATE signuplpro SET gameshosted = '" + gamescreated + "' WHERE username = '" + user + "';");

        }
        statement.close();
    }

    /**
     * Method to get ranking
     * @return the ranking info
     * @throws SQLException 
     */
    public String getRanking() throws SQLException {
        char Separator = ((char) 007);
        PostgreSQLink.connect();
        String toreturn = "Rankings";
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT username, wins, hits, losses, gameshosted, gamesjoined FROM signuplpro WHERE id > '" + 0 + "';");
        while (results1.next()) {
            String user = "&";
            user += results1.getInt("wins") + "" + Separator + "" + results1.getInt("hits") + "" + Separator + "" + results1.getInt("gameshosted") + "" + Separator + "" + results1.getInt("gamesjoined") + "" + Separator + "" + results1.getInt("losses") + "" + Separator + "" + results1.getString("username");
            toreturn += user;
        }
        toreturn += "&";
        String[] printing = toreturn.split("&");
        int c = 0;
        int j = printing.length;
        System.out.println(toreturn);
        System.out.println(j);
        while (c < j) {
            System.out.println(printing[c]);
            c++;
        }
        return toreturn;
    }

}
