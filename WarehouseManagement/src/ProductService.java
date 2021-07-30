import java.util.ArrayList;
import java.util.List;

public class ProductService {
	private List<Product> productList = new ArrayList<>();
	
	public List<Product> getAll() {
		return productList;
	}
	
	public Product add(String name, float price, int quantity) {
		int productId = 0;
		
		if (productList.size() == 0) {
			productId = 1;
		} else {
			productId = productList.get(productList.size() - 1).getId() + 1;
		}
		try {
			Product product = new Product(productId, name, price, quantity);
			productList.add(product);
			
			return product;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean update(Product productUpdate, String name, float price, int quantity, int option) {
		int id = productUpdate.getId();
		Product productTmp = null;
		
		for (int i = 0; i < productList.size(); i++) {
			productTmp = productList.get(i);
			if (productTmp.getId() == id) {
				try {
					if (option == 1) {
						productTmp.setName(name);
					} else if (option == 2)  {
						productTmp.setPrice(price);
					} else if (option == 3) {
						productTmp.setQuantity(quantity);
					}
					
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
	
	public Product getByName(String productName) {
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getName().equalsIgnoreCase(productName)) {
				return productList.get(i);
			}
		}
		return null;
	}
	
	public boolean delete(String productNameDelete) {
		try {
			productList.remove(getByName(productNameDelete));
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
