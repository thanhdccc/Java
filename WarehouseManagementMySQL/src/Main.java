import java.util.List;

import com.fabbi.entity.Product;
import com.fabbi.management.CategoryManagement;
import com.fabbi.management.OrderManagement;
import com.fabbi.management.ProductManagement;
import com.fabbi.service.CategoryService;
import com.fabbi.service.OrderService;
import com.fabbi.service.ProductService;
import com.fabbi.util.Helper;

public class Main {

	private static Helper helper = null;
	private static CategoryManagement categoryHelper = null;
	private static ProductManagement productHelper = null;
	private static OrderManagement orderHelper = null;
	private static CategoryService categoryService = null;
	private static ProductService productService = null;
	private static OrderService orderService = null;

	public static void main(String[] args) {

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
				categoryHelper.totalProductOfCategory();
				break;
			case 5:
				List<Product> productList = orderHelper.top10BestSellerProduct(orderService);
				if (productList != null) {
					System.out.println("****** Top 10 best seller products ******");
					int count = 1;
					for (Product product : productList) {
						System.out.printf("Top %d -> Product ID: %d - Product Name: %s - Sold Quantity: %d", 
								count, product.getProductId(), product.getProductName(), product.getProductQuantity());
						System.out.println();
						count++;
					}
				} else {
					System.out.println("Order list empty!");
				}
				break;
			case 6:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
