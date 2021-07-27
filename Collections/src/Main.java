import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<Product> products = new ArrayList<Product>();
		List<Color> colors = new ArrayList<Color>();
		List<Color> matching = new ArrayList<Color>();
		List<Product> searchName = new ArrayList<Product>();
		
		colors.add(new Color(1, "Xanh"));
		colors.add(new Color(2, "Do"));
		colors.add(new Color(3, "Vang"));
		
		int number;
		boolean check_color;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input number of Product: ");
		number = scanner.nextInt();
		
		//Nhap product
		for (int i = 0; i < number; i++) {
			
			System.out.print("Product ID: ");
			int product_id = scanner.nextInt();
			System.out.println();
			scanner.nextLine();
			
			System.out.print("Product Name: ");
			String product_name = scanner.nextLine();
			System.out.println();
			
			System.out.print("Product Price: ");
			int product_price = scanner.nextInt();
			System.out.println();
			scanner.nextLine();
			
			//Nhap check product color
			do{
				System.out.print("Product Color: ");
				String product_color = scanner.nextLine();
				System.out.println();
				matching = colors.stream()
						.filter(c -> c.getName().equalsIgnoreCase(product_color))
						.collect(Collectors.toList());
				if(matching.size() == 0) {
					System.out.println("Enter color again!");
					check_color = true;
				} else {
					check_color = false;
				}
			} while (check_color);
			products.add(new Product(product_id, product_name, matching.get(0).getId(), product_price));
		}
		
		//In danh sach product
		products.forEach(System.out::println);
		
		//Sap xep theo price tang dan
		products.sort(Comparator.comparing(Product::getPrice));
		System.out.println("After sort");
		products.forEach(System.out::println);
		
		//Tim kiem theo ten product
		System.out.print("Enter name of product: ");
		String search_name = scanner.nextLine();
		searchName = products.stream()
				.filter(p -> p.getName().contains(search_name))
				.collect(Collectors.toList());
		if(searchName.size() == 0) {
			System.out.println("Not Found");
		}else {
			System.out.println("Exist product with name: " + search_name);
		}
		
		//Sap xep danh sach theo ten
		products.sort(Comparator.comparing(Product::getName));
		System.out.println("After sort");
		products.forEach(System.out::println);
		
		//Tim kiem product theo ten mau
		do{
			System.out.print("Enter name of color: ");
			String search_color = scanner.nextLine();
			
			matching.clear();
			matching = colors.stream()
					.filter(c -> c.getName().equalsIgnoreCase(search_color))
					.collect(Collectors.toList());
			if(matching.size() == 0) {
				System.out.println("Not found that color: " + search_color);
				check_color = true;
			} else {
				check_color = false;
			}
		} while (check_color);
		int color_id = matching.get(0).getId();
		searchName.clear();
		searchName = products.stream()
				.filter(p -> p.getColorid() == color_id)
				.collect(Collectors.toList());
		if(searchName.size() == 0) {
			System.out.println("Not Found");
		}else {
			System.out.println("Exist product");
			searchName.forEach(System.out::println);
		}
		scanner.close();
	}
}
