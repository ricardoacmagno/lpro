package LogicServer;

import java.io.IOException;
import ServerCommunication.MultiServer;

/**
 * Main class of the server side of the communication
 *
 * @author ricar
 */
public class MainServer {

    /**
     * Main method that creates a new class <code>MultiServer</code>
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("SERVER INITIALIZED");
        MultiServer socket = new MultiServer();
        socket.openSocket();
    }
}
