import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int option = 0;
		boolean checkOption = true;
		boolean checkChoose = true;
		
		do {
			System.out.println("---------------------------------------");
			System.out.println("1. NullPointerException.");
			System.out.println("2. ArrayIndexOutOfBoundsException.");
			System.out.println("3. NumberFormatException.");
			System.out.println("4. FileNotFoundException.");
			System.out.println("5. Exit.");
			System.out.println("---------------------------------------");
			do {
				System.out.print("Enter your choise: ");
				try {
					option = scanner.nextInt();
					if (option <= 5 || option >= 1) {
						checkChoose = false;
					}
				} catch (InputMismatchException ex) {
					System.out.println("Message: " + ex.toString());
					System.out.println("Please input number (1-5)!");
				}
				scanner.nextLine();
			} while (checkChoose);
			switch (option) {
			case 1:
				String checkNullPoiter = null;
				System.out.print("Enter a String: ");
				String input = scanner.nextLine();
				try {
					if (checkNullPoiter.equals(input)) {
						System.out.println("OK");
					} else {
						System.out.println("NOT OK");
					}
				} catch (NullPointerException e) {
					System.out.println("Message: " + e.toString());
				}
				break;
			case 2:
				int [] checkArrayIndex;
				int sizeArray = 0;
				boolean checkInteger = true;
				Random random = new Random();
				
				do {
					System.out.print("Size of Array: ");
					try {
						sizeArray = scanner.nextInt();
						checkInteger = false;
					} catch (InputMismatchException e) {
						System.out.println("Message: " + e.toString());
					}
					System.out.println();
					scanner.nextLine();
				} while (checkInteger);
				checkArrayIndex = new int [sizeArray];
				for (int i = 0; i < sizeArray; i++) {
					checkArrayIndex[i] = random.nextInt(100);
				}
				try {
					for (int i = 0; i <= sizeArray; i++) {
						System.out.print(checkArrayIndex[i] + "\t");
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println();
					System.out.println("Message: " + e.toString());
				}
				break;
			case 3:
				System.out.print("Enter a String: ");
				String checkNumber = scanner.nextLine();
				try {
					int integer = Integer.parseInt(checkNumber);
					System.out.println("Number after parse: " + integer);
				} catch (NumberFormatException e) {
					System.out.println("Message: " + e.toString());
				}
				break;
			case 4:
				String address = "c://";
				System.out.print("Enter file name: ");
				String name = scanner.nextLine();
				String filename = address + name + ".txt";
//				try {
//					Stream<String> stream = Files.lines(Paths.get(filename));
//					stream.forEach(System.out::println);
//				} catch (FileNotFoundException e) {
//					System.out.println("Message: " + e.toString());
//				} catch (IOException e) {
//					System.out.println("Message: " + e.toString());
//				}
				try {
					BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
					reader.readLine();
				} catch (FileNotFoundException e) {
					System.out.println("Message: " + e.toString());
				} catch (IOException e) {
					System.out.println("Message: " + e.toString());
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
		scanner.close();
	}
}
