package com.fabbi.entity;

public class CategoryDetail extends Category{

	private int totalProduct;

	public CategoryDetail() {
		super();
	}

	public CategoryDetail(int categoryId, String categoryName, int totalProduct) {
		super(categoryId, categoryName);
		this.totalProduct = totalProduct;
	}

	public int getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	
	public String toString() {
		return super.toString() + String.format(" - Total Products: %d", totalProduct);
	}
}
