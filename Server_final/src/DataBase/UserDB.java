package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricar
 */
public final class UserDB extends PostgreSQLink{

    private final String username;
    private String password;
    private String email;
    private Integer question;
    private String answer;
    
    public UserDB(String username) throws Exception  {
        this.username = username;
        this.getLine();
    }
    public void getLine(){
        PostgreSQLink.connect();
        try {
            Statement statement = getConnection().createStatement();

            ResultSet results = statement.executeQuery("SELECT password FROM signuplpro WHERE username = '"+this.username+"';");
            if (results.next()){
                    this.password=results.getString("password");
            }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
    }  
    public String getUsername(){
        if(this.password==null) return "Error&";
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public Boolean getEmail(String email){
        try {
            Statement statement = getConnection().createStatement();

            ResultSet results = statement.executeQuery("SELECT username FROM signuplpro WHERE email = '"+email+"';");
            if (results.next()){
                    return true;
            }
        } catch (Exception ex) {
            System.err.println("Error!" + ex.getMessage());
        }
        return false;
    }
    
    public void newLine(String Username, String Password, String Name, String Mail){
        Statement statement;
        
        try {
            statement = getConnection().createStatement();
            statement.executeQuery("INSERT INTO signuplpro (name, email, username, password) VALUES ('"+Name+"','"+Mail+"', '"+Username+"', '"+Password+"', DEFAULT)");
        }
        catch (Exception ex) {
            if(!ex.getMessage().equals("No results were returned by the query."))
                Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
