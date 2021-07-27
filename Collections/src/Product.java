
public class Product {
	private int id;
	private String name;
	private int colorid;
	private int price;
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, int colorid, int price) {
		super();
		this.id = id;
		this.name = name;
		this.colorid = colorid;
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
	
	public int getColorid() {
		return colorid;
	}
	
	public void setColorid(int colorid) {
		this.colorid = colorid;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		return String.format("id: %d - name: %s - color_id: %d - price: %d", id, name, colorid, price);
	}
}
