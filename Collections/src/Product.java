
public class Product {
	private int id;
	private String name;
	private int colorId;
	private int price;
	
	public Product() {
	}
	
	public Product(int id, String name, int colorId, int price) {
		this.id = id;
		this.name = name;
		this.colorId = colorId;
		this.price = price;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getColorId() {
		return colorId;
	}
	
	public void setColorid(int colorId) {
		this.colorId = colorId;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return String.format("id: %d - name: %s - color_id: %d - price: %d", id, name, colorId, price);
	}
}
