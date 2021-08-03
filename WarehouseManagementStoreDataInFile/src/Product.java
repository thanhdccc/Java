import java.io.Serializable;

public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2829115848223660218L;
	
	private int productId;
	private String productName;
	private float productPrice;
	private int productQuantity;
	private int categoryId;
	
	public Product() {
		
	}
	
	public Product(int productId, String productName, float productPrice, int productQuantity, int categoryId) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return String.format("Product ID: %d - Product Name: %s - Product Price: %.4f - Product Quantity: %d - Category ID: %d", productId, productName, productPrice, productQuantity, getCategoryId());
	}
}
