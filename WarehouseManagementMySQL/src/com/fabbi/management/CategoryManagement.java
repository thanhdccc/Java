package com.fabbi.management;
import java.util.List;

import com.fabbi.entity.Category;
import com.fabbi.entity.CategoryDetail;
import com.fabbi.entity.Product;
import com.fabbi.service.CategoryServiceImpl;
import com.fabbi.service.ProductServiceImpl;
import com.fabbi.util.Helper;

public class CategoryManagement {

	private Helper helper = null;
	private static  CategoryManagement instance;
	
	private CategoryManagement() {
		
	}
	
	public static CategoryManagement getInstance() {
		if (instance == null) {
			instance = new CategoryManagement();
		}
		return instance;
	}

	public void getTotalProduct(CategoryServiceImpl categoryService) {
		
		List<CategoryDetail> list = categoryService.totalProductOfCategory();
		if (list.size() == 0) {
			System.out.println("List of Category is empty!");
		} else {
			list.forEach(System.out::println);
		}
	}

	public void categoryManagementExecute(CategoryServiceImpl categoryService, ProductServiceImpl productService) {

		helper = Helper.getInstance();
		int option = 0;
		boolean checkOption = true;
		List<Category> categoryList = null;
		Category category = null;

		do {
			System.out.println();
			System.out.println(">>>>>> Category Management <<<<<<");
			helper.subMenu("Category");
			option = helper.inputOption(1, 5);

			switch (option) {
			case 1:
				categoryList = categoryService.getAll();

				if (categoryList.size() == 0) {
					System.out.println("List of Category is empty!");
				} else {
					categoryList.forEach(System.out::println);
				}
				break;
			case 2:
				categoryList = categoryService.getAll();
				
				String categoryName = helper.inputCategoryName(categoryList, 0, 0);
				category = new Category(categoryName);
				boolean resultAdd = categoryService.add(category);

				if (resultAdd) {
					System.out.println("Add success!");
				} else {
					System.out.println("Add failed!");
				}
				break;
			case 3:
				categoryList = categoryService.getAll();
				
				if (categoryList.size() == 0) {
					System.out.println("There is no Category to update!");
				} else {
					String categoryNameOld = helper.inputCategoryName(categoryList, 1, 0);
					String categoryNameNew = helper.inputCategoryName(categoryList, 0, 1);
					category = categoryService.getByName(categoryNameOld);
					category.setCategoryName(categoryNameNew);
					boolean resultUpdate = categoryService.update(category);

					if (resultUpdate) {
						System.out.println("Update success!");
					} else {
						System.out.println("Update failed!");
					}
				}
				break;
			case 4:
				categoryList = categoryService.getAll();
				
				if (categoryList.size() == 0) {
					System.out.println("There is no Category to delete!");
				} else {
					String categoryNameDelete = helper.inputCategoryName(categoryList, 1, 0);
					category = categoryService.getByName(categoryNameDelete);
					List<Product> productList = productService.getByCategoryId(category.getCategoryId());
					
					if (productList.size() == 0) {
						boolean resultDelete = categoryService.delete(category);

						if (resultDelete) {
							System.out.println("Delete success!");
						} else {
							System.out.println("Delete failed!");
						}
					} else {
						System.out.printf("There is a Product belong to %s category. Delete failed!",
								categoryNameDelete);
						System.out.println();
					}
				}
				break;
			case 5:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
