package ServerCommunication;

import LogicServer.Chat;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class responsible for creating multiple server threads so that one server can
 * handle multiple clients
 *
 * @author ricar
 */
public class MultiServer {
    public static Chat chat = null;
    /**
     * Method responsible for starting a new socket and calling
     * <code>MultiServerThread</code>
     *
     * @throws IOException
     */
    public void openSocket() throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(1633);
            System.out.println("Waiting on 1633.");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1633.");
            System.exit(-1);
        }
        chat = new Chat();
        while (listening) {
            new MultiServerThread(serverSocket.accept(), chat).start();
        }
        serverSocket.close();
    }
}
