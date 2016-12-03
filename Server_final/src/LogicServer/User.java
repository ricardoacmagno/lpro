package LogicServer;

import DataBase.UserDB;

/**
 * Class responsible for handling the conection between the server and the database
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
    public static UserDB userData;
    
    /**
     * Constructor
     * @param name
     * @param email
     * @param username
     * @param password
     * @param question
     * @param anwser
     * @throws Exception 
     */
    public User (String name, String email, String username, String password, Integer question, String anwser) throws Exception{
        this.name=name;
        this.email=email;
        this.username= username;
        this.password=password;
        this.question=question;
        this.anwser=anwser;
    }
    
    // NOT SURE IF NECESSARY FOR NOW //
    /*public User(String username) {
        this.username = username;
    }*/
    /**
     * @return <code>String</code> containing user's name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return <code>String</code> containing user's email
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * @return <code>String</code> containing user's password hash
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * @return <code>String</code> containing user's username
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * @return <code>Integer</code> containing user's safety question id
     */
    public Integer getQuestion(){
        return question;
    }
    
    /**
     * @return <code>String</code> containing user's safety question answer
     */
    public String getAnwser(){
        return anwser;
    }
    
    /**
     * Method that creates a new class <code>UserDB</code> in order to make the necessary verifications in the database
     * @param received
     * @return <code>Boolean</code> with the value 1 if the username is in the database and has a password associated or 0 if it isn't
     * @throws Exception 
     */
    public static boolean confirmUsername(String received) throws Exception{
        System.out.println("USER.RECEIVED: "+received);
        userData=new UserDB();
        userData.setUsername(received);
        userData.getLine();
        //Chamar a parte do DB >> verificar se encontrou uma lista através daquele username
        
        return received.equals(userData.getUsername());                //SAME AS WHAT FOLLOWS
        
    }
    
    /**
     * Method that verifies if the password written by the user is correct
     * @param received
     * @return <code>Boolean</code> with the value 1 if the password is correct or 0 in case of error
     */
    public static boolean confirmPassword(String received){
        System.out.println("HASH: " + received);
        //Chamar a parte do DB >> verificar a palavra passe retornada com aquela recebida  
        return received.equals(userData.getPassword());              //CRIAR O GETPASSWORD NO USERDB PARA RETORNAR A PALAVRA PASSE
    }
    
    public static String[] UserCheckGame(String user){
        String[] opponent=userData.getGame(user);
        return opponent;
    }
    public static String CheckOpponent(String id){
        int gameid=Integer.parseInt(id);
        String opponent=userData.CheckOpponent(gameid);
        return opponent;
    }
    
    /**
     * Method WTF E QUE ISTO FAZ XDXDXD
     * @param received
     * @return <code>Boolean</code> true if the email is in the database or false if it isn't
     * @throws Exception 
     */
   public static boolean confirmEmailForPassword(String received) throws Exception{
       
       userData=new UserDB();
       userData.setEmail(received);
      
       return userData.getEmail(received);
   }
    
   /**
    * Method that verifies is the email picked by the user is already in use
    * @param received
    * @return <code>Boolean</code> true if the email is in the database or false if it isn't
    */
    public static boolean confirmEmail(String received) {           //verifica se existe um email na base
        System.out.println("EMAIL.RECEIVED: "+ received);
     
        return userData.getEmail(received);                //SAME AS WHAT FOLLOWS
    }
    
    /**
     * Method that uses the <code>UserDB</code> class to insert the signup parameters in the database
     * @param data 
     */
    public static void sendSignup(String[] data){
        userData.newLine(data[1], data[3], data[4], data[2]);   //Manda primeiro o Username, Password, Nome e Email
    }
    
    /**
     * Method that uses the <code>UserDB</code> class to change a user's password 
     * @param ChangePassword
     * @return ?????? RETURN DO UPDATE DA DATABASE ?????
     */
    public static int sendForgotPassword(String[] ChangePassword){
       return userData.newPass(ChangePassword[1], ChangePassword[2], ChangePassword[3], ChangePassword[4]);
    }
}
