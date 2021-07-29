import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	
	private static String inputName(List<Category> listCategories, Scanner scanner, int option1, int option2) {
		List<Category> matchingCategory = new ArrayList<>();
		boolean checkCategoryName = true;
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
					checkCategoryName = true;
				}
			} else if (option1 == 1) {
				if (matchingCategory.size() == 0) {
					System.out.println("Not Exist!");
					checkCategoryName = true;
				} else {
					return name;
				}
			}
			
		} while (checkCategoryName);
		return null;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<Category> listCategories = new ArrayList<>();
		int option = 0;
		boolean checkOption = true;
		boolean checkChoose = true;

		do {
			System.out.println("---------------------------------------");
			System.out.println("1. Quan ly Category.");
			System.out.println("2. Quan ly Product.");
			System.out.println("3. Quan ly Order.");
			System.out.println("4. Thong ke so luong moi Category.");
			System.out.println("5. Thong ke 10 san ban chay.");
			System.out.println("6. Exit.");
			System.out.println("---------------------------------------");
			
			do {
				System.out.print("Enter your choise: ");
				try {
					option = scanner.nextInt();
					if (option <= 6 || option >= 1) {
						checkChoose = false;
					}
				} catch (InputMismatchException ex) {
					System.out.println("Message: " + ex.toString());
					System.out.println("Please input number (1-6)!");
				}
				scanner.nextLine();
			} while (checkChoose);
			
			switch (option) {
			case 1:
				int optionCase1 = 0;
				boolean checkOptionCase1 = true;
				boolean checkChooseCase1 = true;
				do {
					System.out.println("---------------");
					System.out.println("1. List.");
					System.out.println("2. Add.");
					System.out.println("3. Edit.");
					System.out.println("4. Delete.");
					System.out.println("5. Back.");
					System.out.println("---------------");
					
					do {
						System.out.print("Enter your choise: ");
						try {
							optionCase1 = scanner.nextInt();
							if (optionCase1 <= 5 || optionCase1 >= 1) {
								checkChooseCase1 = false;
							}
						} catch (InputMismatchException ex) {
							System.out.println("Message: " + ex.toString());
							System.out.println("Please input number (1-5)!");
						}
						scanner.nextLine();
					} while (checkChooseCase1);
					
					switch (optionCase1) {
					case 1:
						if (listCategories.size() == 0) {
							System.out.println("List of Category is empty!");
						} else {
							listCategories.forEach(System.out::println);
						}
						break;
					case 2:
						int categoryId = 0;
						if (listCategories.size() == 0) {
							categoryId = 1;
						} else {
							categoryId = listCategories.get(listCategories.size() - 1).getId() + 1;
						}
						String categoryName = inputName(listCategories, scanner, 0, 0);
						try {
							listCategories.add(new Category(categoryId, categoryName));
							System.out.println("Add success!");
						} catch (Exception e) {
							System.out.println("Add failed!");
						}
						break;
					case 3:
						String categoryNameEdit = inputName(listCategories, scanner, 1, 0);
						String newCategoryName = inputName(listCategories, scanner, 0, 1);
						for (int i = 0; i < listCategories.size(); i++) {
							if (listCategories.get(i).getName().equals(categoryNameEdit)) {
								try {
									listCategories.get(i).setName(newCategoryName);
									System.out.println("Edit success!");
								} catch (Exception e) {
									System.out.println("Edit failed!");
								}
							}
						}
						break;
					case 4:
						String categoryNameDelete = inputName(listCategories, scanner, 1, 0);
						for (int i = 0; i < listCategories.size(); i++) {
							if (listCategories.get(i).getName().equals(categoryNameDelete)) {
								try {
									listCategories.remove(i);
									i--;
									System.out.println("Delete success!");
									break;
								} catch (Exception e) {
									System.out.println("Delete failed!");
								}
							}
						}
						break;
					case 5:
						checkOptionCase1 = false;
						break;
					}
				} while (checkOptionCase1);
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
