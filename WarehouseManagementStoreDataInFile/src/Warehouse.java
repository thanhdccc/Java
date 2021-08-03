import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3596262470629758804L;
	
	private List<Category> categoryList;
	private List<Product> productList;
	private List<Order> orderList;
	
	public Warehouse() {
		categoryList = new ArrayList<>();
		productList = new ArrayList<>();
		orderList = new ArrayList<>();
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
