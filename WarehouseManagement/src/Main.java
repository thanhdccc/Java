import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	private static String inputName(List<Category> listCategories, Scanner scanner, int option1, int option2) {
		List<Category> matchingCategory = new ArrayList<>();
		do {
			if (option2 == 0) {
				System.out.print("Enter category name: ");
			} else if (option2 == 1) {
				System.out.print("Enter new category name: ");
			}
			String name = scanner.nextLine();
			System.out.print("");
			matchingCategory = listCategories.stream()
					.filter(c -> c.getName().equalsIgnoreCase(name))
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
	
	private static int inputOption(int min, int max, Scanner scanner) {
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
	
	private static void subMenu() {
		System.out.println("---------------");
		System.out.println("1. List.");
		System.out.println("2. Add.");
		System.out.println("3. Edit.");
		System.out.println("4. Delete.");
		System.out.println("5. Back.");
		System.out.println("---------------");
	}
	
	private static void categoryManagement(List<Category> listCategories, Scanner scanner) {
		CategoryService service = new CategoryService();
		int option = 0;
		boolean checkOption = true;
		do {
			subMenu();
			option = inputOption(1, 5, scanner);
			scanner.nextLine();
			switch (option) {
			case 1:
				service.listCategory(listCategories);
				break;
			case 2:
				String categoryName = inputName(listCategories, scanner, 0, 0);
				service.addCategory(listCategories, categoryName);
				break;
			case 3:
				String oldCategoryName = inputName(listCategories, scanner, 1, 0);
				String newCategoryName = inputName(listCategories, scanner, 0, 1);
				service.editCategory(listCategories,oldCategoryName, newCategoryName);
				break;
			case 4:
				String deleteCategoryName = inputName(listCategories, scanner, 1, 0);
				service.deleteCategory(listCategories, deleteCategoryName);
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		List<Category> listCategories = new ArrayList<>();
		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println("---------------------------------------");
			System.out.println("1. Quan ly Category.");
			System.out.println("2. Quan ly Product.");
			System.out.println("3. Quan ly Order.");
			System.out.println("4. Thong ke so luong moi Category.");
			System.out.println("5. Thong ke 10 san ban chay.");
			System.out.println("6. Exit.");
			System.out.println("---------------------------------------");
			
			option = inputOption(1, 6, scanner);
			
			switch (option) {
			case 1:
				categoryManagement(listCategories, scanner);
				break;
			case 2:
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
		
		scanner.close();
	}
}
