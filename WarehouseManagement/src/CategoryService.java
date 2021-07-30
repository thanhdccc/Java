import java.util.List;

public class CategoryService {

	public void listCategory(List<Category> listCategories) {
		if (listCategories.size() == 0) {
			System.out.println("List of Category is empty!");
		} else {
			listCategories.forEach(System.out::println);
		}
	}

	public void addCategory(List<Category> listCategories, String categoryName) {
		int categoryId = 0;
		if (listCategories.size() == 0) {
			categoryId = 1;
		} else {
			categoryId = listCategories.get(listCategories.size() - 1).getId() + 1;
		}
		try {
			listCategories.add(new Category(categoryId, categoryName));
			System.out.println("Add success!");
		} catch (Exception e) {
			System.out.println("Add failed!");
		}
	}
	
	public void editCategory(List<Category> listCategories, String oldCategoryName, String newCategoryName) {
		for (int i = 0; i < listCategories.size(); i++) {
			if (listCategories.get(i).getName().equals(oldCategoryName)) {
				try {
					listCategories.get(i).setName(newCategoryName);
					System.out.println("Edit success!");
				} catch (Exception e) {
					System.out.println("Edit failed!");
				}
			}
		}
	}
	
	public void deleteCategory(List<Category> listCategories, String deleteCategoryName) {
		for (int i = 0; i < listCategories.size(); i++) {
			if (listCategories.get(i).getName().equals(deleteCategoryName)) {
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
	}
}
