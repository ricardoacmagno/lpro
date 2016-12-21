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
 *
 * @author Francisco
 */
public class Listen extends Thread {

    String fromServer;
    Socket kkSocket;
    boolean connected = true;
    User user = null;

    Listen(Socket mysocket, User myuser) {
        this.kkSocket = mysocket;
        this.user = myuser;
    }

    @Override

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
            try {
                sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] echo = fromServer.split("&");
            user.refreshData(echo);
        }
    }
}
