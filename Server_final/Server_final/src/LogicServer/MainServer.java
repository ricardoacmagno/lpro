package LogicServer;

import java.io.IOException;
import ServerCommunication.MultiServer;

/**
 *
 * @author ricar
 */
public class MainServer {
    public static void main(String[] args) throws IOException{
        System.out.println("SERVER INITIALIZED");
        MultiServer socket = new MultiServer();
        socket.openSocket(); 
    }
}
