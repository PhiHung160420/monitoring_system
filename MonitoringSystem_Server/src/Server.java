import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;


public class Server implements Runnable {
	private int port;
//	private static Server server;
    public static ArrayList<Socket> clients = null;
    public static Vector<String> clientName = null;
    public static ServerSocket serverSocket = null;
    public static boolean checked = true;
    
    public Server() {}

    public Server(int port) throws IOException {
        this.port = port;
    }
    
//    public static Server getInstance() {
//    	 if (server == null) {
//    		 server = new Server();
//         }
//         return server;
//    }

    public void run() {
        Socket socket = null;
        clients = new ArrayList<Socket>();
        clientName = new Vector<String>();
        try {
            serverSocket = new ServerSocket(port);
           
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        while (checked) {
            try {
            	socket = serverSocket.accept();
                clients.add(socket);
                
//                new Thread(new ServerReceive(s, listaClient, nameClient, map)).start();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.frame, "Close Server！");
            }
        }
    }
    
    public static void resetServer() throws IOException {
    	try {
    		serverSocket.close();
        	serverSocket = null;
        	clients = null;
        	checked = false;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}