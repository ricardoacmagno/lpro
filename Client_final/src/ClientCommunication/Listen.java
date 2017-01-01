/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientCommunication;

import LogicClient.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>Listen</code> is a thread that is always searching for info of the server
 *
 * @author ilia
 */
public class Listen extends Thread {

    String fromServer;
    Socket kkSocket;
    boolean connected = true;
    User user = null;
    
    /**
     * Constructor
     * @param mysocket is a socket to exchange info
     * @param myuser  is an object of the class user
     */

    Listen(Socket mysocket, User myuser) {
        this.kkSocket = mysocket;
        this.user = myuser;
    }

    @Override
/**
 * Runnable of the thread so that the client can receive from the server
 */
    public void run() {
        while (connected) {
            fromServer = null;
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            } catch (IOException ex) {
                connected = false;
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (fromServer == null) {
                    fromServer = in.readLine();
                }
            } catch (IOException ex) {
                connected = false;
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Received from Thread: " + fromServer);
            String[] echo = fromServer.split("&");
            try {
                user.refreshData(echo);
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
                connected = false;
            } catch (InterruptedException ex) {
                connected = false;
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
