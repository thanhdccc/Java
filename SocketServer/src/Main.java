import java.util.Scanner;

public class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter port: ");
		int port = scanner.nextInt();
		System.out.println();
		
		ChatServer server = new ChatServer(port);
		server.execute();
	}
}
