package ClientCommunication;

import GUI.UIinicial;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SocketClient represents the client side of the Client-Server connection
 * Responsible for starting this connection
 *
 * @author ilia
 */
public class SocketClient extends Socket {

    public Socket kkSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    InputStreamReader input = null;

    /**
     * Initializes the connection.
     *
     * @throws IOException
     */
    public void openSocket(UIinicial ui) throws IOException {
        try {

            kkSocket = new Socket("gnomo.fe.up.pt", 1633);

            System.out.println("Socket opened!");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: gnomo.fe.up.pt");
            ui.get1().showMessageDialog(null, "Can't connect to server");
            System.exit(1);
        } catch (IOException e) {
            ui.get1().showMessageDialog(null, "Can't get info from server");
            System.err.println("Couldn't get I/O for the connection to: gnomo.fe.up.pt");
            System.exit(1);
        }
    }
/**
 * Method to get the used socket
 * @return the used socket 
 */
    public Socket getSocket() {
        return kkSocket;
    }

    /**
     * Method responsible for receiving responses from the server
     *
     * @return <code>String</code> with what was read from the server
     * @throws IOException
     */
    public String received() throws IOException {
        String fromServer = null;
        try {
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            fromServer = in.readLine();
            System.out.println("Received from server: " + fromServer);

        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fromServer;
    }

    /**
     * Method responsible for sending response strings to the server
     *
     * @param fromUser String with information to send
     * @throws IOException
     */
    public void toSend(String fromUser) throws IOException {
        out = new PrintWriter(kkSocket.getOutputStream(), true);
        if (fromUser != null) {
            System.out.println("Sent to server: " + fromUser);
            out.println(fromUser);
        }
    }

    /**
     * Method responsible for closing the connection and all its communication
     * ports
     */
    @Override
    public void close() {
        try {
            in.close();
            out.close();
            kkSocket.close();
            System.out.println("Socket closed!");
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
    }
}
