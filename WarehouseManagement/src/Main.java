import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	private static CategoryService categoryService = null;
	private static ProductService productService = null;

	private static String inputCategoryName(List<Category> categoryList, int option1, int option2) {
		List<Category> matchingCategory = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option2 == 0) {
				System.out.print("Enter category name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new category name: ");
			}
			String name = scanner.nextLine();
			System.out.print("");
			matchingCategory = categoryList.stream().filter(c -> c.getName().equalsIgnoreCase(name))
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

	private static String inputProductName(List<Product> productList, int option1, int option2) {
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
			matchingProduct = productList.stream().filter(c -> c.getName().equalsIgnoreCase(name))
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

	private static float inputProductPrice(int option) {
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

	private static int inputProductQuantity(int option) {
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

	private static int inputOption(int min, int max) {
		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.print("Enter your choise: ");
			try {
				option = scanner.nextInt();
				if (option <= max || option >= min) {

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

	private static void subProductManagementMenu() {
		System.out.println("---------------");
		System.out.println("1. Update product name.");
		System.out.println("2. Update product price.");
		System.out.println("3. Update product quantity.");
		System.out.println("4. Back.");
		System.out.println("---------------");
	}

	private static void productUpdateManagement(String productNameOld) {
		Scanner scanner = new Scanner(System.in);
		if (productService == null) {
			productService = new ProductService();
		}
		int option = 0;
		boolean checkOption = true;
		String productNameNew = null;
		boolean isNameUpdated = false;
		Product productTmp;
		boolean resultUpdate;

		do {
			subProductManagementMenu();
			option = inputOption(1, 4);

			switch (option) {
			case 1:
				productNameNew = inputProductName(productService.getAll(), 0, 1);
				productTmp = productService.getByName(productNameOld);
				resultUpdate = productService.update(productTmp, productNameNew, 0, 0, 1);
				
				if (resultUpdate == true) {
					isNameUpdated = true;
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 2:
				float productPriceNew = inputProductPrice(1);
				productTmp = isNameUpdated ? productService.getByName(productNameNew)
						: productService.getByName(productNameOld);
				resultUpdate = productService.update(productTmp, null, productPriceNew, 0, 2);
				
				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 3:
				int productQuantityNew = inputProductQuantity(1);
				productTmp = isNameUpdated ? productService.getByName(productNameNew)
						: productService.getByName(productNameOld);
				resultUpdate = productService.update(productTmp, null, 0, productQuantityNew, 3);
				
				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 4:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void subMenu() {
		System.out.println("---------------");
		System.out.println("1. List.");
		System.out.println("2. Add.");
		System.out.println("3. Edit.");
		System.out.println("4. Delete.");
		System.out.println("5. Back.");
		System.out.println("---------------");
	}

	private static void mainMenu() {
		System.out.println("---------------------------------------");
		System.out.println("1. Quan ly Category.");
		System.out.println("2. Quan ly Product.");
		System.out.println("3. Quan ly Order.");
		System.out.println("4. Thong ke so luong moi Category.");
		System.out.println("5. Thong ke 10 san ban chay.");
		System.out.println("6. Exit.");
		System.out.println("---------------------------------------");
	}

	private static void categoryManagement() {
		Scanner scanner = new Scanner(System.in);
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		int option = 0;
		boolean checkOption = true;
		
		do {
			subMenu();
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				List<Category> categoryList = categoryService.getAll();
				
				if (categoryList.size() == 0) {
					System.out.println("List of Category is empty!");
				} else {
					categoryList.forEach(System.out::println);
				}
				break;
			case 2:
				String categoryName = inputCategoryName(categoryService.getAll(), 0, 0);
				Category resultAdd = categoryService.add(categoryName);
				
				if (resultAdd != null) {
					System.out.println("Add success!");
				} else {
					System.out.println("Add failed!");
				}
				break;
			case 3:
				String categoryNameOld = inputCategoryName(categoryService.getAll(), 1, 0);
				String categoryNameNew = inputCategoryName(categoryService.getAll(), 0, 1);
				Category categoryTmp = categoryService.getByName(categoryNameOld);
				boolean resultUpdate = categoryService.update(categoryTmp, categoryNameNew);
				
				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 4:
				String categoryNameDelete = inputCategoryName(categoryService.getAll(), 1, 0);
				boolean resultDelete = categoryService.delete(categoryNameDelete);
				
				if (resultDelete == true) {
					System.out.println("Delete success!");
				} else {
					System.out.println("Delete failed!");
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	private static void productManagement() {
		Scanner scanner = new Scanner(System.in);
		if (productService == null) {
			productService = new ProductService();
		}
		int option = 0;
		boolean checkOption = true;
		
		do {
			subMenu();
			option = inputOption(1, 5);

			switch (option) {
			case 1:
				List<Product> productList = productService.getAll();
				
				if (productList.size() == 0) {
					System.out.println("List of Product is empty!");
				} else {
					productList.forEach(System.out::println);
				}
				break;
			case 2:
				String productName = inputProductName(productService.getAll(), 0, 0);
				float productPrice = inputProductPrice(0);
				int productQuantity = inputProductQuantity(0);
				Product resultAdd = productService.add(productName, productPrice, productQuantity);
				
				if (resultAdd != null) {
					System.out.println("Add success!");
				} else {
					System.out.println("Add failed!");
				}
				break;
			case 3:
				String productNameOld = inputProductName(productService.getAll(), 1, 0);
				productUpdateManagement(productNameOld);
				break;
			case 4:
				String productNameDelete = inputProductName(productService.getAll(), 1, 0);
				boolean resultDelete = productService.delete(productNameDelete);
				
				if (resultDelete == true) {
					System.out.println("Delete success!");
				} else {
					System.out.println("Delete failed!");
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	public static void main(String[] args) {
		int option = 0;
		boolean checkOption = true;

		do {
			mainMenu();
			option = inputOption(1, 6);

			switch (option) {
			case 1:
				categoryManagement();
				break;
			case 2:
				productManagement();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
