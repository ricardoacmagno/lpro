package ServerCommunication;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author ricar
 */
public class MultiServer {
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
 
      while (listening)
	 new MultiServerThread(serverSocket.accept()).start();
      serverSocket.close();
   }
}
