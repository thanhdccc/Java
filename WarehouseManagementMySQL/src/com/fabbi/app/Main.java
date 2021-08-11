package com.fabbi.app;
import com.fabbi.management.CategoryManagement;
import com.fabbi.management.OrderManagement;
import com.fabbi.management.ProductManagement;
import com.fabbi.service.CategoryServiceImpl;
import com.fabbi.service.OrderServiceImpl;
import com.fabbi.service.ProductServiceImpl;
import com.fabbi.util.Helper;

public class Main {

	private static Helper helper = null;
	private static CategoryManagement categoryHelper = null;
	private static ProductManagement productHelper = null;
	private static OrderManagement orderHelper = null;
	private static CategoryServiceImpl categoryService = null;
	private static ProductServiceImpl productService = null;
	private static OrderServiceImpl orderService = null;

	public static void main(String[] args) {

		helper = Helper.getInstance();
		
		categoryService = CategoryServiceImpl.getInstance();
		productService = ProductServiceImpl.getInstance();
		orderService = OrderServiceImpl.getInstance();
		
		categoryHelper = CategoryManagement.getInstance();
		productHelper = ProductManagement.getInstance();
		orderHelper = OrderManagement.getInstance();
		
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
				categoryHelper.getTotalProduct(categoryService);
				break;
			case 5:
				orderHelper.getTop10BestSoldProduct(orderService);
				break;
			case 6:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
