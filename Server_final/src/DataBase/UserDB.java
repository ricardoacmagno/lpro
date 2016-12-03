package DataBase;

import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for making the database changes
 * @author ilia&magno
 */
public final class UserDB extends PostgreSQLink{

    private String username;
    private String password;
    private String email;
    private Integer question;
    private String answer;
    
    Statement statement = null;
    
    public UserDB() throws Exception  {
       
      
    }
    
    /**
     * Constructor
     * @param username 
     */
    public void setUsername(String username){
        this.username=username;
    }
    
    /**
     * Constructor
     * @param email 
     */
    public void setEmail(String email){
        this.email=email;
    }
    
    /**
     * Method that gets the password from the database based in a username
     */
    public void getLine(){
        PostgreSQLink.connect();
        try {
           statement = getConnection().createStatement();

            ResultSet results = statement.executeQuery("SELECT password FROM signuplpro WHERE username = '"+this.username+"';");
            if (results.next()){
                    this.password=results.getString("password");
            }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
    }
    
    /**
     * @return <code>String</code> with username or error if the username has no password attached
     */
    public String getUsername(){
        if(this.password==null) return "Error&";
        return username;
    }
    
    /**
     * @return <code>String</code> with password
     */
    public String getPassword(){
        return password;
    }
    
    
    public String[] getGame(String user){
        String opponent=null;
        int id=0;
        try {
            System.out.println("Checking game...");
            PostgreSQLink.connect();
           // Statement statement = getConnection().createStatement();
            statement = getConnection().createStatement();
            ResultSet results1 = statement.executeQuery("SELECT player1name, id FROM gamesrunning WHERE player1joined = '"+true+"' AND player2joined = '"+false+"';");
            if (results1.next()){
                opponent=results1.getString("player1name");
                id=results1.getInt("id");
                System.out.println(user+" don't need to create a game");
                statement.executeUpdate("UPDATE gamesrunning SET player2joined = '"+true+"', player2name = '"+user+"' WHERE player1joined = '"+true+"';");
                results1.close();
                statement.close();
            }
            else{
                System.out.println(user+" need to create a game");
                statement.executeUpdate("INSERT INTO "
                        + "gamesrunning(player1name, player2name, player1joined, player2joined, shipsplayer1, shipsplayer2, player1ready, player2ready, winner) "
                        + "VALUES ('"+user+"','"+"default"+"','"+true+"','"+false+"','"+null+"','"+null+"','"+false+"','"+false+"','"+null+"');");
                try (ResultSet results5 = statement.executeQuery("SELECT player1name, id FROM gamesrunning WHERE player1joined = '"+true+"' AND player2joined = '"+false+"';")) {
                    if (results5.next())
                        id=results5.getInt("id");
                    System.out.println(id);
                    results5.close();
                }
                opponent="default";
                
                statement.close();
            }
        } catch (Exception ex) {
            if(!ex.getMessage().equals("No results were returned by the query."))
                System.err.println("Error!" + ex.getMessage());
        }
        String oops = id+"";
        String[] toReturn={opponent,oops};
        return toReturn; 
    }
    
    /**
     * Method that verifies if an email exists in the database
     * @param email
     * @return <code>Boolean</code> true if it exists or false if it doesn't
     */
    public Boolean getEmail(String email){
        
        try {
            PostgreSQLink.connect();
           // Statement statement = getConnection().createStatement();
            statement = getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT email FROM signuplpro WHERE email = '"+email+"';");
            if (results.next()){
                    return true;
            }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
        return false;
    }
    public String CheckOpponent(int id){
        PostgreSQLink.connect();
        String opponent="default";
        System.out.println("Finding opponent");
        try {
            //mudar
            while(opponent.equals("default")){
            statement = getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT player2name FROM gamesrunning WHERE id = '"+id+"';");
            if (results.next()){
                    opponent=results.getString("player2name");
            }
            statement.close();
            results.close();
            sleep(500);
           }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
        return opponent;
    }
    
    /**
     * Method that inserts into the database the signup information of a new user
     * @param Username
     * @param Password
     * @param Name
     * @param Mail 
     */
    public void newLine(String Username, String Password, String Name, String Mail){
      
        try {
            statement = getConnection().createStatement();
            //System.out.println("statemente ="  + statement);
            statement.executeQuery("INSERT INTO signuplpro(name, email, username, password) VALUES ('"+Name+"','"+Mail+"','"+Username+"','"+Password+"');");  //"' DEFAULT");
        }
        catch (Exception e) {
            if(!e.getMessage().equals("No results were returned by the query."))
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Method responsible for changing a user's password based on his username, email and old password
     * @param mail
     * @param Username
     * @param OldPassword
     * @param Password
     * @return ???????
     */
    public int  newPass(String mail, String Username, String OldPassword ,String Password){
        System.out.println("ESTOU AQUI");
        try{
             PostgreSQLink.connect();
            statement = getConnection().createStatement();
            
            
            return statement.executeUpdate("UPDATE signuplpro SET password='"+Password+"' WHERE  email='"+mail+"' and username = '"+Username+"' and password='"+OldPassword+"';");  
            
        }catch (Exception e) {
            
            System.err.println("Error!" + e.getMessage());
            if(!e.getMessage().equals("No results were returned by the query."))
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, e);
            
        }
        return -1;
        
    }
}
