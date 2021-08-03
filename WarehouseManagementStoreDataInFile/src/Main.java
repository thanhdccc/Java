import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Main {

	private static Helper helper = null;
	private static CategoryManagement categoryHelper = null;
	private static ProductManagement productHelper = null;
	private static OrderManagement orderHelper = null;
	private static CategoryService categoryService = null;
	private static ProductService productService = null;
	private static OrderService orderService = null;
	private static final String DATA_FILE = "Data.txt";
	
	private static Warehouse readDataFromFile() {
		File file = new File(DATA_FILE);
		Warehouse warehouse = new Warehouse();
		
		if(file.exists()) {
			try {
				FileInputStream dataFile = new FileInputStream(file);
				ObjectInputStream inputStream = new ObjectInputStream(dataFile);
				
				warehouse = (Warehouse) inputStream.readObject();
				
				inputStream.close();
				dataFile.close();
				
				return warehouse;
				
			} catch (FileNotFoundException e) {
				System.out.println("Error: " + e);
				return null;
			} catch (IOException e) {
				System.out.println("Error: " + e);
				return null;
			} catch (ClassNotFoundException e) {
				System.out.println("Error: " + e);
				return null;
			}
		} else {
			return null;
		}
	}
	
	private static void writeDataToFile() {
		try {
			File file = new File(DATA_FILE);
			
			if (!file.exists()) {
				file.createNewFile();
				
				Warehouse warehouse = new Warehouse();
				warehouse.setCategoryList(categoryService.getAll());
				warehouse.setProductList(productService.getAll());
				warehouse.setOrderList(orderService.getAll());
				
				FileOutputStream dataFile = new FileOutputStream(file);
				ObjectOutputStream outputStream = new ObjectOutputStream(dataFile);
				
				outputStream.writeObject(warehouse);
				
				outputStream.close();
				dataFile.close();
				
			} else {
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
				
				Warehouse warehouse = new Warehouse();
				warehouse.setCategoryList(categoryService.getAll());
				warehouse.setProductList(productService.getAll());
				warehouse.setOrderList(orderService.getAll());
				
				FileOutputStream dataFile = new FileOutputStream(file);
				ObjectOutputStream outputStream = new ObjectOutputStream(dataFile);
				
				outputStream.writeObject(warehouse);
				
				outputStream.close();
				dataFile.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Warehouse warehouse = null;
		warehouse = readDataFromFile();
		
		if (warehouse == null) {
			System.out.println("No data to load!");
			
			if (helper == null) {
				helper = new Helper();
			}
			if (categoryHelper == null) {
				categoryHelper = new CategoryManagement();
			}
			if (productHelper == null) {
				productHelper = new ProductManagement();
			}
			if (orderHelper == null) {
				orderHelper = new OrderManagement();
			}
			if (categoryService == null) {
				categoryService = new CategoryService();
			}
			if (productService == null) {
				productService = new ProductService();
			}
			if (orderService == null) {
				orderService = new OrderService();
			}
			int option = 0;
			boolean checkOption = true;

			do {
				System.out.println();
				System.out.println(">>>>>>------ Main Menu ------<<<<<<");
				helper.mainMenu();
				option = helper.inputOption(1, 6);

				switch (option) {
				case 1:
					categoryHelper.categoryManagementExecute(categoryService, productService);
					break;
				case 2:
					productHelper.productManagementExecute(categoryService, productService);
					break;
				case 3:
					orderHelper.orderManagementExecute(categoryService, productService, orderService);
					break;
				case 4:
					categoryHelper.totalProductOfCategory(categoryService, productService);
					break;
				case 5:
					List<Product> productList = orderHelper.top10BestSellerProduct(orderService);
					if (productList != null) {
						Product product = null;
						System.out.println("****** Top 10 best seller products ******");
						if (productList.size() >= 10) {
							for (int i = 0; i < 10; i++) {
								product = productList.get(i);
								System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(),
										product.getProductQuantity());
								System.out.println();
							}
						} else {
							for (int i = 0; i < productList.size(); i++) {
								product = productList.get(i);
								System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(),
										product.getProductQuantity());
								System.out.println();
							}
						}
					}
					break;
				case 6:
					writeDataToFile();
					checkOption = false;
					break;
				}
			} while (checkOption);
		} else {
			if (helper == null) {
				helper = new Helper();
			}
			if (categoryHelper == null) {
				categoryHelper = new CategoryManagement();
			}
			if (productHelper == null) {
				productHelper = new ProductManagement();
			}
			if (orderHelper == null) {
				orderHelper = new OrderManagement();
			}
			if (categoryService == null) {
				categoryService = new CategoryService();
				categoryService.setCategoryList(warehouse.getCategoryList());
			}
			if (productService == null) {
				productService = new ProductService();
				productService.setProductList(warehouse.getProductList());
			}
			if (orderService == null) {
				orderService = new OrderService();
				orderService.setOrderList(warehouse.getOrderList());
			}
			int option = 0;
			boolean checkOption = true;

			do {
				System.out.println();
				System.out.println(">>>>>>------ Main Menu ------<<<<<<");
				helper.mainMenu();
				option = helper.inputOption(1, 6);

				switch (option) {
				case 1:
					categoryHelper.categoryManagementExecute(categoryService, productService);
					break;
				case 2:
					productHelper.productManagementExecute(categoryService, productService);
					break;
				case 3:
					orderHelper.orderManagementExecute(categoryService, productService, orderService);
					break;
				case 4:
					categoryHelper.totalProductOfCategory(categoryService, productService);
					break;
				case 5:
					List<Product> productList = orderHelper.top10BestSellerProduct(orderService);
					if (productList != null) {
						Product product = null;
						System.out.println("****** Top 10 best seller products ******");
						if (productList.size() >= 10) {
							for (int i = 0; i < 10; i++) {
								product = productList.get(i);
								System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(),
										product.getProductQuantity());
								System.out.println();
							}
						} else {
							for (int i = 0; i < productList.size(); i++) {
								product = productList.get(i);
								System.out.printf("Product Name: %s - Quantity: %d", product.getProductName(),
										product.getProductQuantity());
								System.out.println();
							}
						}
					}
					break;
				case 6:
					writeDataToFile();
					checkOption = false;
					break;
				}
			} while (checkOption);
		}
	}
}
