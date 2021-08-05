import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static APIService apiService = null;
	private static CrawlDataService crawlService = null;

	private static void mainMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Call API.");
		System.out.println("2. Download Image.");
		System.out.println("3. Exit.");
		System.out.println("---------------------------------------");
	}
	
	private static void subMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Create (POST).");
		System.out.println("2. List (GET).");
		System.out.println("3. Update (PUT).");
		System.out.println("4. Delete (DELETE).");
		System.out.println("5. Back.");
		System.out.println("---------------------------------------");
	}
	
	@SuppressWarnings("resource")
	private static int inputOption(int min, int max) {

		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.print("Enter your choise: ");
			try {
				option = scanner.nextInt();

				if (option > max || option < min) {
					System.out.printf("Please input number (%d-%d)!", min, max);
					System.out.println();
				} else {

					return option;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Message: " + ex.toString());
				System.out.printf("Please input number (%d-%d)!", min, max);
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}
	
	@SuppressWarnings("resource")
	private static int inputInteger() {
		
		Scanner scanner = new Scanner(System.in);
		int id;

		do {
			System.out.print("Enter ID: ");
			try {
				id = scanner.nextInt();
				
				return id;
			} catch (InputMismatchException ex) {
				System.out.println("Message: " + ex.toString());
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}
	
	public static void main(String[] args) {
		
		if (apiService == null) {
			apiService = new APIService();
		}
		if (crawlService == null) {
			crawlService = new CrawlDataService();
		}

		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>>------ Main Menu ------<<<<<<");
			mainMenu();
			option = inputOption(1, 3);

			switch (option) {
			case 1:
				int optionCase1 = 0;
				boolean checkOptionCase1 = true;
				
				do {
					System.out.println();
					System.out.println(">>>>>>------ Call API ------<<<<<<");
					subMenu();
					optionCase1 = inputOption(1, 5);

					switch (optionCase1) {
					case 1:
						apiService.create();
						break;
					case 2:
						int idGet = inputInteger();
						apiService.getById(idGet);
						break;
					case 3:
						int idUpdate = inputInteger();
						apiService.updateById(idUpdate);
						break;
					case 4:
						int idDelete = inputInteger();
						apiService.deleteById(idDelete);
						break;
					case 5:
						checkOptionCase1 = false;
						break;
					}
				} while (checkOptionCase1);
				break;
			case 2:
				crawlService.execute();
				break;
			case 3:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
