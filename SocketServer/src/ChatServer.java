import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
	
	private int port;
	private Set<String> userNameList = new HashSet<>();
	private Set<User> userList = new HashSet<>();
	
	public ChatServer(int port) {
		this.port = port;
	}
	
	public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
 
                User newUser = new User(socket, this);
                userList.add(newUser);
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            ex.printStackTrace();
        }
    }

	public void broadcast(String message, User excludeUser) {
        for (User aUser : userList) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }

	public void addUserName(String userName) {
    	userNameList.add(userName);
    }

	public void removeUser(String userName, User aUser) {
        boolean removed = userNameList.remove(userName);
        if (removed) {
        	userList.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
 
	public Set<String> getUserNames() {
        return this.userNameList;
    }

	public boolean hasUsers() {
        return !this.userNameList.isEmpty();
    }
}
