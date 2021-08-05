import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

	private String hostname;
	private int port;
	private String userName;

	public ChatClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);

			System.out.println("Connected to the chat server");

			new Read(socket, this).start();
			new Write(socket, this).start();

		} catch (UnknownHostException ex) {
			System.out.println("Error: " + ex);
		} catch (IOException ex) {
			System.out.println("Error: " + ex);
		}

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
}
