import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendToServer {
	 SendToServer(Socket socket, Object message, String info, String name, String path) throws IOException {
	   String msg = info + ",," + message + ",," + name + ",," + path;
	   PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
	   pwOut.println(msg);
	}
}
