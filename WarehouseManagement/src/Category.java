import java.util.ArrayList;
import java.util.List;

public class Category {
	private int categoryId;
	private String categoryName;
	private List<Product> productList;
	
	public Category() {
		productList = new ArrayList<>();
	}
	
	public Category(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		productList = new ArrayList<>();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public String toString() {
		return String.format("Category ID: %d - Category Name: %s", categoryId, categoryName);
	}
}
