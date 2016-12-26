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
    private Integer question;
    private String answer;

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
     *
     * @param user
     * @return
     */
    public String[] getGame(String user) {
        int id = 0;
        try {
            System.out.println("Creating game...");
            PostgreSQLink.connect();
            // Statement statement = getConnection().createStatement();
            statement = getConnection().createStatement();
            statement.executeUpdate("INSERT INTO "
                    + "gamesrunning(player1name, player2name, player1joined, player2joined, shipsplayer1, shipsplayer2, player1ready, player2ready, winner) "
                    + "VALUES ('" + user + "','" + "default" + "','" + true + "','" + false + "','" + null + "','" + null + "','" + false + "','" + false + "','" + null + "');");
            try (ResultSet results5 = statement.executeQuery("SELECT id FROM gamesrunning WHERE player2joined = '" + false + "' AND player1name = '" + user + "';")) {
                if (results5.next()) {

                    id = results5.getInt("id");
                    System.out.println("Game of " + user + "created with id of" + id);
                }
                System.out.println(id);
                results5.close();
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
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
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
            System.out.println("Info of player " + user + " placed in DB, he joined game of " +opponent +" with game id "+id);
            results1.close();
            statement.close();
        } else {
            ok = "notok";
        }
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
    public void newLine(String Username, String Password, String Name, String Mail) {

        try {
            statement = getConnection().createStatement();
            //System.out.println("statemente ="  + statement);
            statement.executeQuery("INSERT INTO signuplpro(name, email, username, password) VALUES ('" + Name + "','" + Mail + "','" + Username + "','" + Password + "');");  //"' DEFAULT");
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
    public int newPass(String mail, String Username, String OldPassword, String Password) {
        try {
            PostgreSQLink.connect();
            statement = getConnection().createStatement();

            return statement.executeUpdate("UPDATE signuplpro SET password='" + Password + "' WHERE  email='" + mail + "' and username = '" + Username + "' and password='" + OldPassword + "';");

        } catch (Exception e) {

            System.err.println("Error!" + e.getMessage());
            if (!e.getMessage().equals("No results were returned by the query.")) {
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        return -1;

    }

    public void finishGame(Game game) throws SQLException {
        PostgreSQLink.connect();
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT shipsplayer1, shipsplayer2 , winner FROM gamesrunning WHERE id = '" + game.getId() + "';");
        if (results1.next()) {
            statement.executeUpdate("UPDATE gamesrunning SET shipsplayer1 = '" + game.getPlayer2Ships() + "', shipsplayer2 = '" + game.getPlayer2Ships() + "', winner = '" + game.getWinner() + "' WHERE id= '" + game.getId() + "';");
        }
    }

    public void cancelGame(int id) throws SQLException {
        PostgreSQLink.connect();
        statement = getConnection().createStatement();
        ResultSet results1 = statement.executeQuery("SELECT player2joined, winner FROM gamesrunning WHERE id = '" + id + "';");
        if (results1.next()) {
            statement.executeUpdate("UPDATE gamesrunning SET player2joined = '" + true + "', winner = '" + "canceled" + "' WHERE id= '" + id + "';");
        }
    }
}
