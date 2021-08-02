import java.util.List;

public class CategoryManagement {

	private Helper helper = null;
	
	public void totalProductOfCategory(CategoryService categoryService, ProductService productService) {
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		if (categoryService.getAll().size() == 0) {
			System.out.println("There is no Category!");
		} else {
			for (Category category : categoryService.getAll()) {

				int total = productService.getByCategoryId(category.getCategoryId()).size();
				System.out.printf("%s have %d products", category.getCategoryName(), total);
				System.out.println();
			}
		}
	}
	
	public void categoryManagementExecute(CategoryService categoryService, ProductService productService) {
		if (categoryService == null) {
			categoryService = new CategoryService();
		}
		if (productService == null) {
			productService = new ProductService();
		}
		if (helper == null) {
			helper = new Helper();
		}
		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>> Category Management <<<<<<");
			helper.subMenu("Category");
			option = helper.inputOption(1, 5);

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
				String categoryName = helper.inputCategoryName(categoryService.getAll(), 0, 0);
				Category resultAdd = categoryService.add(categoryName);

				if (resultAdd != null) {
					System.out.println("Add success!");
				} else {
					System.out.println("Add failed!");
				}
				break;
			case 3:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category to update!");
				} else {
					String categoryNameOld = helper.inputCategoryName(categoryService.getAll(), 1, 0);
					String categoryNameNew = helper.inputCategoryName(categoryService.getAll(), 0, 1);
					Category categoryTmp = categoryService.getByName(categoryNameOld);
					categoryTmp.setCategoryName(categoryNameNew);
					boolean resultUpdate = categoryService.update(categoryTmp);

					if (resultUpdate == true) {
						System.out.println("Update success!");
					} else {
						System.out.println("Update failed!");
					}
				}
				break;
			case 4:
				if (categoryService.getAll().size() == 0) {
					System.out.println("There is no Category to delete!");
				} else {
					String categoryNameDelete = helper.inputCategoryName(categoryService.getAll(), 1, 0);
					Category categoryTmp = categoryService.getByName(categoryNameDelete);
					if (productService.getByCategoryId(categoryTmp.getCategoryId()).size() == 0) {
						boolean resultDelete = categoryService.delete(categoryNameDelete);

						if (resultDelete == true) {
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
