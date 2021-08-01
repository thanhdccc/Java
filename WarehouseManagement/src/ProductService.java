import java.util.ArrayList;
import java.util.List;

public class ProductService {
	private List<Product> productList = new ArrayList<>();

	public List<Product> getAll(CategoryService categoryService, String categoryName) {
		productList = categoryService.getByName(categoryName).getProductList();
		return productList;
	}

	public boolean add(CategoryService categoryService, String categoryName, String name, float price, int quantity) {
		productList = categoryService.getByName(categoryName).getProductList();
		int productId = 0;

		if (productList.size() == 0) {
			productId = 1;
		} else {
			productId = productList.get(productList.size() - 1).getProductId() + 1;
		}
		try {
			Product product = new Product(categoryService.getByName(categoryName).getCategoryId(), categoryName, productId, name, price, quantity);
			productList.add(product);
			categoryService.getByName(categoryName).setProductList(productList);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean update(CategoryService categoryService, String categoryName, Product productUpdate, String name, float price, int quantity,
			int option) {
		productList = categoryService.getByName(categoryName).getProductList();
		int id = productUpdate.getProductId();
		Product productTmp = null;

		for (int i = 0; i < productList.size(); i++) {
			productTmp = productList.get(i);
			if (productTmp.getProductId() == id) {
				try {
					if (option == 1) {
						productTmp.setProductName(name);
					} else if (option == 2) {
						productTmp.setProductPrice(price);
					} else if (option == 3) {
						productTmp.setProductQuantity(quantity);
					}

					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	
	public boolean moveCategory(CategoryService categoryService, String categoryOld, String categoryNew, String productName) {
		productList = categoryService.getByName(categoryOld).getProductList();
		List<Product> productListTmp = categoryService.getByName(categoryNew).getProductList();
		Product productOld = getByName(categoryService, categoryOld, productName);
		if (productListTmp.size() == 0) {
			try {
				productList.remove(productOld);
				productOld.setProductId(1);
				productOld.setCategoryId(categoryService.getByName(categoryNew).getCategoryId());
				productOld.setCategoryName(categoryService.getByName(categoryNew).getCategoryName());
				productListTmp.add(productOld);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			for (Product product : productListTmp) {
				if (product.getProductName().equalsIgnoreCase(productOld.getProductName())){
					return false;
				} else {
					try {
						productList.remove(productOld);
						productOld.setProductId(productListTmp.get(productListTmp.size() - 1).getProductId() + 1);
						productOld.setCategoryId(categoryService.getByName(categoryNew).getCategoryId());
						productOld.setCategoryName(categoryService.getByName(categoryNew).getCategoryName());
						productListTmp.add(productOld);
						return true;
					} catch (Exception e) {
						return false;
					}
				}
			}
		}
		return false;
	}

	public Product getByName(CategoryService categoryService, String categoryName, String productName) {
		productList = categoryService.getByName(categoryName).getProductList();
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getProductName().equalsIgnoreCase(productName)) {
				return productList.get(i);
			}
		}
		return null;
	}

	public boolean delete(CategoryService categoryService, String categoryName, String productNameDelete) {
		productList = categoryService.getByName(categoryName).getProductList();
		try {
			productList.remove(getByName(categoryService, categoryName, productNameDelete));

			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
