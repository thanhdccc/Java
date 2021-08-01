import java.util.ArrayList;
import java.util.List;

public class CategoryService {

	private List<Category> categoryList = new ArrayList<>();

	public List<Category> getAll() {
		return categoryList;
	}

	public Category add(String categoryName) {
		int categoryId = 0;
		if (categoryList.size() == 0) {
			categoryId = 1;
		} else {
			categoryId = categoryList.get(categoryList.size() - 1).getCategoryId() + 1;
		}
		try {
			Category category = new Category(categoryId, categoryName);
			categoryList.add(category);

			return category;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean update(Category categoryUpdate, String newName) {
		int id = categoryUpdate.getCategoryId();
		Category categoryTmp = null;

		for (int i = 0; i < categoryList.size(); i++) {
			categoryTmp = categoryList.get(i);
			if (categoryTmp.getCategoryId() == id) {
				try {
					categoryTmp.setCategoryName(newName);
					
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	
	public int getTotalProductByCategoryName(String categoryName) {
		return getByName(categoryName).getProductList().size();
	}

	public Category getByName(String categoryName) {
		for (int i = 0; i < categoryList.size(); i++) {
			if (categoryList.get(i).getCategoryName().equals(categoryName)) {
				return categoryList.get(i);
			}
		}
		return null;
	}

	public boolean delete(String categoryNameDelete) {
		try {
			categoryList.remove(getByName(categoryNameDelete));
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
