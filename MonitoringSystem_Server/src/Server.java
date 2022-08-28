import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	public static void main(String[] args) throws UnknownHostException {	 
        final int PORT = 3000;
        InetAddress addr = InetAddress.getLocalHost();
        try (ServerSocket serverSocket = new ServerSocket(PORT, 0, addr)) {
        	
            System.out.println("Server is listening on IP Address: " + addr.getHostAddress() + " and on port: " + PORT);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
 
                new ServerThread(socket).start();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
	}

}