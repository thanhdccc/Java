import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Write extends Thread {

	private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
 
    public Write(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
 
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
 
    @SuppressWarnings("resource")
	public void run() {
    	Scanner scanner = new Scanner(System.in);

    	System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.println();
        
        client.setUserName(userName);
        writer.println(userName);
 
        String text;
 
        do {
        	System.out.print("[" + userName + "]: ");
            text = scanner.nextLine();
            System.out.println();
            writer.println(text);
 
        } while (!text.equals("bye"));
 
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
