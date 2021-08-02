import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Helper {

	@SuppressWarnings("resource")
	public String inputCategoryName(List<Category> categoryList, int option1, int option2) {

		List<Category> matchingCategory = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter category name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new category name: ");
			} else if (option2 == 2) {
				System.out.print("Enter Category Name that Product belong to: ");
			}

			String name = scanner.nextLine();
			System.out.print("");
			matchingCategory = categoryList.stream().filter(c -> c.getCategoryName().equalsIgnoreCase(name))
					.collect(Collectors.toList());

			if (option1 == 0) {
				if (matchingCategory.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {

				if (matchingCategory.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	@SuppressWarnings("resource")
	public String inputProductName(List<Product> productList, int option1, int option2) {

		List<Product> matchingProduct = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter product name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new product name: ");
			}

			String name = scanner.nextLine();
			System.out.print("");
			matchingProduct = productList.stream().filter(c -> c.getProductName().equalsIgnoreCase(name))
					.collect(Collectors.toList());

			if (option1 == 0) {
				if (matchingProduct.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {

				if (matchingProduct.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	@SuppressWarnings("resource")
	public String inputOrderName(List<Order> orderList, int option1, int option2) {

		List<Order> matchingOrder = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {

			if (option2 == 0) {
				System.out.print("Enter order name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new order name: ");
			}

			String name = scanner.nextLine();
			System.out.print("");
			matchingOrder = orderList.stream().filter(c -> c.getOrderName().equalsIgnoreCase(name))
					.collect(Collectors.toList());

			if (option1 == 0) {
				if (matchingOrder.size() == 0) {

					return name;
				} else {
					System.out.println("Duplicate!");
				}
			} else if (option1 == 1) {

				if (matchingOrder.size() == 0) {
					System.out.println("Not Exist!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	@SuppressWarnings("resource")
	public float inputProductPrice(int option) {

		Scanner scanner = new Scanner(System.in);
		float price;

		do {

			if (option == 0) {
				System.out.print("Enter product price: ");
			} else if (option == 1) {
				System.out.print("Enter new product price: ");
			}

			try {
				price = scanner.nextFloat();

				return price;
			} catch (Exception e) {
				System.out.println("Message: " + e.toString());
				System.out.println("Please input number!");
			}
			scanner.nextLine();
		} while (true);
	}

	@SuppressWarnings("resource")
	public int inputProductQuantity(int option) {

		Scanner scanner = new Scanner(System.in);
		int quantity;

		do {
			if (option == 0) {
				System.out.print("Enter product quantity: ");
			} else if (option == 1) {
				System.out.print("Enter new product quantity: ");
			}

			try {
				quantity = scanner.nextInt();

				return quantity;
			} catch (Exception e) {
				System.out.println("Message: " + e.toString());
				System.out.println("Please input number!");
			}
			scanner.nextLine();
		} while (true);
	}

	@SuppressWarnings("resource")
	public int inputOption(int min, int max) {

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

	public void subProductManagementMenu() {

		System.out.println("---------------");
		System.out.println("1. Update product name.");
		System.out.println("2. Update product price.");
		System.out.println("3. Update product quantity.");
//		System.out.println("4. Move category.");
		System.out.println("4. Back.");
		System.out.println("---------------");
	}

	public void subMenu(String name) {

		System.out.println("---------------");
		System.out.println("1. List " + name + ".");
		System.out.println("2. Add " + name + ".");
		System.out.println("3. Edit " + name + ".");
		System.out.println("4. Delete " + name + ".");
		System.out.println("5. Back.");
		System.out.println("---------------");
	}

	public void mainMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Quan ly Category.");
		System.out.println("2. Quan ly Product.");
		System.out.println("3. Quan ly Order.");
		System.out.println("4. Thong ke so luong moi Category.");
		System.out.println("5. Thong ke 10 san pham ban chay.");
		System.out.println("6. Exit.");
		System.out.println("---------------------------------------");
	}
}
