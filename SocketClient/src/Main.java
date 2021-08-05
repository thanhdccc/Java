import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter hostname: ");
		String hostName = scanner.nextLine();
		System.out.println();
		
		System.out.print("Enter port: ");
		int port = scanner.nextInt();
		System.out.println();
		scanner.nextLine();
		
		ChatClient client = new ChatClient(hostName, port);
		client.execute();
	}
}
