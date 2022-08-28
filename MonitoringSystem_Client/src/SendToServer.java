import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServer {
	 SendToServer(Socket socket, Object message, String type, String name, String path) throws IOException {
	   String msg = type + ",," + message + ",," + name + ",," + path;
	   PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
	   pwOut.println(msg);
	}
}
