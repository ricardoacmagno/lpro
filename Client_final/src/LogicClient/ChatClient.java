/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicClient;

import ClientCommunication.SocketClient;
import java.io.DataInputStream;

import java.io.*;

/**
 *
 * @author ilia-
 */
public class ChatClient implements Runnable {
    
    private SocketClient socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
    
}
