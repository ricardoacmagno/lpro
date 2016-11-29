/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ricar
 */
public class PostgreSQLink {
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }
    static void connect(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.err.println(e.getMessage());}
        
    // Connect to the database
    Connection con = null;
    try { con = DriverManager.getConnection( "jdbc:postgresql://dbm.fe.up.pt/lpro1633", "lpro1633", "Y!1380xuw");
        connection=con;
        }catch (SQLException e){
            System.err.println(e.getMessage());}
        }

    static void close(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLink.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
