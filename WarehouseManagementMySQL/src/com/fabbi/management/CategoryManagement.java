package com.fabbi.management;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fabbi.entity.Category;
import com.fabbi.service.CategoryService;
import com.fabbi.service.ProductService;
import com.fabbi.util.DBUtil;
import com.fabbi.util.Helper;

public class CategoryManagement {

	private Helper helper = null;

	public void totalProductOfCategory() {

		DBUtil dbUtil = new DBUtil();
		
		Connection con = null;
		String sql = null;
		int numberOfProduct = 0;
		String name = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select count(a.product_id) as number_product, b.category_name from products as a "
					+ "inner join categories as b on a.category_id = b.category_id "
					+ "group by b.category_name";
			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				numberOfProduct = result.getInt(1);
				name = result.getString(2);
				
				System.out.printf("Category %s have %d product(s).", name, numberOfProduct);
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void categoryManagementExecute(CategoryService categoryService, ProductService productService) {

		if (helper == null) {
			helper = new Helper();
		}
		int option = 0;
		boolean checkOption = true;
		List<Category> categoryList = null;

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
				String categoryName = helper.inputCategoryName(categoryService.getAll(), 0, 0);
				boolean resultAdd = categoryService.add(categoryName);

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
					Category categoryTmp = categoryService.getByName(categoryNameOld);
					categoryTmp.setCategoryName(categoryNameNew);
					boolean resultUpdate = categoryService.update(categoryTmp);

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
					Category categoryTmp = categoryService.getByName(categoryNameDelete);
					if (productService.getByCategoryId(categoryTmp.getCategoryId()).size() == 0) {
						boolean resultDelete = categoryService.delete(categoryNameDelete);

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
