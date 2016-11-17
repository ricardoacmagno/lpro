package LogicServer;

import DataBase.UserDB;

/**
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
    public static UserDB userData;
    
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
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUsername(){
        return username;
    }
     
    public Integer getQuestion(){
        return question;
    }
      
    public String getAnwser(){
        return anwser;
    }
    
   
    
    public static boolean confirmUsername(String received) throws Exception{
        System.out.println("USER.RECEIVED: "+received);
        userData=new UserDB(received);
        //Chamar a parte do DB >> verificar se encontrou uma lista através daquele username
        return received.equals(userData.getUsername());                //SAME AS WHAT FOLLOWS
    }
    
    public static boolean confirmPassword(String received){
        System.out.println("HASH: " + received);
        //Chamar a parte do DB >> verificar a palavra passe retornada com aquela recebida  
        return received.equals(userData.getPassword());              //CRIAR O GETPASSWORD NO USERDB PARA RETORNAR A PALAVRA PASSE
    }
    
    public static boolean confirmEmail(String received) throws Exception{
        System.out.println("EMAIL.RECEIVED: "+received);
        //Chamar a parte do DB >> verificar se encontrou uma lista através daquele email
        return userData.getEmail(received);                //SAME AS WHAT FOLLOWS
    }
    
    public static void sendSignup(String[] data){
        System.out.println("DATA1" + data[1]);
        System.out.println("DATA3" +data[3]);
        System.out.println("DATA4" +data[4]);
        System.out.println("DATA2" +data[2]);
        userData.newLine(data[1], data[3], data[4], data[2]);   //Manda primeiro o Username, Password, Nome e Email
    }
}
