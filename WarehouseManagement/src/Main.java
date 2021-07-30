import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	private static CategoryService categoryService = null;
	
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
			matchingCategory = categoryList.stream()
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
			scanner.nextLine();
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
				boolean updateResult = categoryService.update(categoryTmp, categoryNameNew);
				if (updateResult == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 4:
				String categoryNameDelete = inputCategoryName(categoryService.getAll(), 1, 0);
				boolean deleteResult = categoryService.delete(categoryNameDelete);
				if (deleteResult == true) {
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
