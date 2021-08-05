import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class User extends Thread {

	private Socket socket;
	private ChatServer chatServer;
	private PrintWriter writer;

	public User(Socket socket, ChatServer chatServer) {
		this.socket = socket;
		this.chatServer = chatServer;
	}

	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			printUsers();

			String userName = reader.readLine();
			chatServer.addUserName(userName);

			String serverMessage = "New user connected: " + userName;
			chatServer.broadcast(serverMessage, this);

			String clientMessage;

			do {
				clientMessage = reader.readLine();
				serverMessage = "[" + userName + "]: " + clientMessage;
				chatServer.broadcast(serverMessage, this);

			} while (!clientMessage.equals("bye"));

			chatServer.removeUser(userName, this);
			socket.close();

			serverMessage = userName + " has quitted.";
			chatServer.broadcast(serverMessage, this);

		} catch (IOException ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void printUsers() {
		if (chatServer.hasUsers()) {
			writer.println("Connected users: " + chatServer.getUserNames());
		} else {
			writer.println("No other users connected");
		}
	}

	public void sendMessage(String message) {
		writer.println(message);
	}
}
