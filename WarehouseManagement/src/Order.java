import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private int orderId;
	private String orderName;
	private List<Product> productList;
	private float totalPrice;
	
	public Order() {
		productList = new ArrayList<>();
	}

	public Order(int orderId, String orderName, float totalPrice, List<Product> productList) {
		this.orderId = orderId;
		this.orderName = orderName;
		this.totalPrice = totalPrice;
		this.productList = productList;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return String.format("Order ID: %d - Order Name: %s - Total Price: %.4f", orderId, orderName, totalPrice);
	}
}
