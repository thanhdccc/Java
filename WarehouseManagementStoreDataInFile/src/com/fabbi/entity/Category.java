package com.fabbi.entity;
import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 735803346619188274L;
	
	private int categoryId;
	private String categoryName;

	public Category() {
	}

	public Category(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return String.format("Category ID: %d - Category Name: %s", categoryId, categoryName);
	}
}
