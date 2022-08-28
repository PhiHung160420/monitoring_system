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
    public static ArrayList<Socket> clients = null;
    public static Vector<String> clientName = null;
    public static ServerSocket serverSocket = null;
    public static boolean checked = true;

    public Server(int porta) throws IOException {
        this.port = porta;
    }

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
}