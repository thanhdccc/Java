package com.fabbi.management;
import java.util.List;
import java.util.stream.Collectors;

import com.fabbi.entity.Category;
import com.fabbi.entity.Product;
import com.fabbi.service.CategoryService;
import com.fabbi.service.ProductService;
import com.fabbi.util.Helper;

public class ProductManagement {

	private Helper helper = null;

	public void productUpdateManagement(String categoryName, String productNameOld, ProductService productService) {

		if (helper == null) {
			helper = new Helper();
		}
		int option = 0;
		boolean checkOption = true;
		String productNameNew = null;
		boolean isNameUpdated = false;
		Product productTmp;
		boolean resultUpdate;

		do {
			helper.subProductManagementMenu();
			option = helper.inputOption(1, 4);

			switch (option) {
			case 1:
				productNameNew = helper.inputProductName(productService.getAll(), 0, 1);
				productTmp = productService.getByName(productNameOld);
				productTmp.setProductName(productNameNew);
				resultUpdate = productService.update(productTmp);

				if (resultUpdate == true) {
					isNameUpdated = true;
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 2:
				float productPriceNew = helper.inputProductPrice(1);
				productTmp = isNameUpdated ? productService.getByName(productNameNew)
						: productService.getByName(productNameOld);
				productTmp.setProductPrice(productPriceNew);
				resultUpdate = productService.update(productTmp);

				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
			case 3:
				int productQuantityNew = helper.inputProductQuantity(1);
				productTmp = isNameUpdated ? productService.getByName(productNameNew)
						: productService.getByName(productNameOld);
				productTmp.setProductQuantity(productQuantityNew);
				resultUpdate = productService.update(productTmp);

				if (resultUpdate == true) {
					System.out.println("Update success!");
				} else {
					System.out.println("Update failed!");
				}
				break;
//			case 4:
//				String categoryNameNew = inputCategoryName(categoryService.getAll(), 1, 1);
//				String productNameTmp = isNameUpdated ? productNameNew : productNameOld;
//				resultUpdate = productService.moveCategory(categoryService, categoryName, categoryNameNew,
//						productNameTmp);
//
//				if (resultUpdate == true) {
//					System.out.println("Move success!");
//				} else {
//					System.out.println("Move failed!");
//				}
//				break;
			case 4:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	public void productManagementExecute(CategoryService categoryService, ProductService productService) {

		if (helper == null) {
			helper = new Helper();
		}
		int option = 0;
		boolean checkOption = true;
		String categoryName;
		List<Product> productList;
		List<Category> categoryList;
		List<Product> matching;

		do {
			System.out.println();
			System.out.println(">>>>>> Product Management <<<<<<");
			helper.subMenu("Product");
			option = helper.inputOption(1, 5);

			switch (option) {
			case 1:
				if (categoryService.getAll().size() == 0) {
					System.out.println("Empty!");
				} else {
					productList = productService.getAll();
					categoryList = categoryService.getAll();

					for (Category category : categoryList) {
						System.out.printf("------ %s ------", category.getCategoryName());
						System.out.println();
						if (productList.size() == 0) {
							System.out.println("List of Product is empty!");
						} else {
							matching = productList.stream().filter(p -> p.getCategoryId() == category.getCategoryId())
									.collect(Collectors.toList());
							matching.forEach(System.out::println);
							matching.clear();
						}
					}
				}
				break;
			case 2:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					categoryName = helper.inputCategoryName(categoryService.getAll(), 1, 2);
					Category categoryTmp = categoryService.getByName(categoryName);
					String productName = helper
							.inputProductName(productService.getByCategoryId(categoryTmp.getCategoryId()), 0, 0);
					float productPrice = helper.inputProductPrice(0);
					int productQuantity = helper.inputProductQuantity(0);

					Product resultAdd = productService.add(productName, productPrice, productQuantity,
							categoryTmp.getCategoryId());

					if (resultAdd != null) {
						System.out.println("Add success!");
					} else {
						System.out.println("Add failed!");
					}
				}
				break;
			case 3:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category exsit. Please add a Category first!");
				} else {
					categoryName = helper.inputCategoryName(categoryService.getAll(), 1, 2);
					Category category = categoryService.getByName(categoryName);
					productList = productService.getByCategoryId(category.getCategoryId());
					if (productList.size() == 0) {
						System.out.printf("There is no Product in %s category to update!", categoryName);
						System.out.println();
					} else {
						String productNameOld = helper.inputProductName(productList, 1, 0);
						productUpdateManagement(categoryName, productNameOld, productService);
					}
				}

				break;
			case 4:
				categoryName = helper.inputCategoryName(categoryService.getAll(), 1, 2);
				Category category = categoryService.getByName(categoryName);
				productList = productService.getByCategoryId(category.getCategoryId());
				if (productList.size() == 0) {
					System.out.printf("There is no Product in %s category to delete!", categoryName);
					System.out.println();
				} else {
					String productNameDelete = helper.inputProductName(productList, 1, 0);
					boolean resultDelete = productService.delete(productNameDelete);

					if (resultDelete == true) {
						System.out.println("Delete success!");
					} else {
						System.out.println("Delete failed!");
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
