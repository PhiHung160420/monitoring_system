import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServer {
	 SendToServer(Socket socket, String type, String name, Object message, String path) throws IOException {
	   String msg = type + "/" + name + "/" + message + "/" + path;
	   PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
	   pwOut.println(msg);
	}
}
