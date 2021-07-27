import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();
		List<Color> colors = new ArrayList<>();
		List<Color> matching = new ArrayList<>();
		List<Product> searchNames = new ArrayList<>();

		colors.add(new Color(1, "Xanh"));
		colors.add(new Color(2, "Do"));
		colors.add(new Color(3, "Vang"));

		boolean checkColor = true;
		boolean checkOption = true;
		boolean checkChoose = true;
		boolean checkInteger = true;
		int option = 0;
		int productId = 0;
		int productPrice = 0;

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("---------------------------------------");
			System.out.println("1. Input Product.");
			System.out.println("2. Display Product List.");
			System.out.println("3. Sort Product List by Price.");
			System.out.println("4. Search Product by Name.");
			System.out.println("5. Sort Product List by Name.");
			System.out.println("6. Search Product by Color Name.");
			System.out.println("7. Exit.");
			System.out.println("---------------------------------------");
			do {
				System.out.print("Enter your choise: ");
				try {
					option = scanner.nextInt();
					if (option <= 7 || option >= 1) {
						checkChoose = false;
					}
				} catch (InputMismatchException ex) {
					System.out.println("Message: " + ex.toString());
					System.out.println("Please input number (1-7)!");
				}
				scanner.nextLine();
			} while (checkChoose);
			switch (option) {
			case 1:
				do {
					System.out.print("Product ID: ");
					try {
						productId = scanner.nextInt();
						checkInteger = false;
					} catch (InputMismatchException e) {
						System.out.println("Message: " + e.toString());
					}
					System.out.println();
					scanner.nextLine();
				} while (checkInteger);

				System.out.print("Product Name: ");
				String productName = scanner.nextLine();
				System.out.println();

				checkInteger = true;
				do {
					System.out.print("Product Price: ");
					try {
						productPrice = scanner.nextInt();
						checkInteger = false;
					} catch (InputMismatchException e) {
						System.out.println("Message: " + e.toString());
					}
					System.out.println();
					scanner.nextLine();
				} while (checkInteger);

				do {
					System.out.print("Product Color: ");
					String productColor = scanner.nextLine();
					System.out.println();
					matching = colors.stream()
							.filter(c -> c.getName().equalsIgnoreCase(productColor))
							.collect(Collectors.toList());
					if (matching.size() == 0) {
						System.out.println("Enter color again!");
						checkColor = true;
					} else {
						checkColor = false;
					}
				} while (checkColor);
				products.add(new Product(productId, productName, matching.get(0).getId(), productPrice));
				break;
			case 2:
				products.forEach(System.out::println);
				break;
			case 3:
				products.sort(Comparator.comparing(Product::getPrice));
				System.out.println("After sort");
				products.forEach(System.out::println);
				break;
			case 4:
				System.out.print("Enter name of product: ");
				String searchName = scanner.nextLine();
				searchNames = products.stream()
						.filter(p -> p.getName().contains(searchName))
						.collect(Collectors.toList());
				if (searchNames.size() == 0) {
					System.out.println("Not Found");
				} else {
					System.out.println("Exist product with name: " + searchName);
				}
				break;
			case 5:
				products.sort(Comparator.comparing(Product::getName));
				System.out.println("After sort");
				products.forEach(System.out::println);
				break;
			case 6:
				do {
					System.out.print("Enter name of color: ");
					String searchColor = scanner.nextLine();

					matching.clear();
					matching = colors.stream()
							.filter(c -> c.getName().equalsIgnoreCase(searchColor))
							.collect(Collectors.toList());
					if (matching.size() == 0) {
						System.out.println("Not found that color: " + searchColor);
						checkColor = true;
					} else {
						checkColor = false;
					}
				} while (checkColor);
				int colorId = matching.get(0).getId();
				searchNames.clear();
				searchNames = products.stream()
						.filter(p -> p.getColorId() == colorId)
						.collect(Collectors.toList());
				if (searchNames.size() == 0) {
					System.out.println("Not Found");
				} else {
					System.out.println("Exist product");
					searchNames.forEach(System.out::println);
				}
				break;
			case 7:
				checkOption = false;
				break;
			}
		} while (checkOption);
		scanner.close();
	}
}
