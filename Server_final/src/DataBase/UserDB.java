package DataBase;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilia&magno
 */
public final class UserDB extends PostgreSQLink{

    private  String username;
    private String password;
    private String email;
    private Integer question;
    private String answer;
    
    Statement statement = null;
    
    public UserDB() throws Exception  {
       
      
    }
    public void setUsername(String username){
        this.username=username;
    }
       public void setEmail(String email){
        this.email=email;
    }
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
    public String getUsername(){
        if(this.password==null) return "Error&";
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
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
