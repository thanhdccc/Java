
public class Product extends Category{
	private int productId;
	private String productName;
	private float productPrice;
	private int productQuantity;
	
	public Product() {
		
	}
	
	public Product(int categoryId, String categoryName, int productId, String productName, float productPrice, int productQuantity) {
		super(categoryId, categoryName);
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
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

	@Override
	public String toString() {
		return String.format("Product ID: %d - Product Name: %s - Product Price: %.4f - Product Quantity: %d - Category ID: %d - Category Name: %s", productId, productName, productPrice, productQuantity, getCategoryId(), getCategoryName());
	}
}
