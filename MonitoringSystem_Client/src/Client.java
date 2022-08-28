import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException {
		final int PORT = 3000;
		InetAddress addr = InetAddress.getByName("127.0.0.1");
        try (Socket socket = new Socket(addr, PORT)) {
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            Console console = System.console();
            String text;
 
            do {
                text = console.readLine("Enter text: ");
 
                writer.println(text);
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String time = reader.readLine();
 
                System.out.println(time);
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
	}
}