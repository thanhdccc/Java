package com.fabbi.service;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Product;

public class ProductService {
	private List<Product> productList = new ArrayList<>();

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Product> getAll() {
		return productList;
	}

	public Product add(String name, float price, int quantity, int categoryId) {

		int productId = 0;

		if (productList.size() == 0) {
			productId = 1;
		} else {
			productId = productList.get(productList.size() - 1).getProductId() + 1;
		}
		try {
			Product product = new Product(productId, name, price, quantity, categoryId);
			productList.add(product);

			return product;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean update(Product productUpdate) {

		int id = productUpdate.getProductId();
		String name = productUpdate.getProductName();
		float price = productUpdate.getProductPrice();
		int quantity = productUpdate.getProductQuantity();
		Product productTmp = null;

		for (int i = 0; i < productList.size(); i++) {
			productTmp = productList.get(i);
			if (productTmp.getProductId() == id) {
				try {
					productTmp.setProductName(name);
					productTmp.setProductPrice(price);
					productTmp.setProductQuantity(quantity);

					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

//		public boolean moveCategory(CategoryService categoryService, String categoryOld, String categoryNew, String productName) {
//			productList = categoryService.getByName(categoryOld).getProductList();
//			List<Product> productListTmp = categoryService.getByName(categoryNew).getProductList();
//			Product productOld = getByName(categoryService, categoryOld, productName);
//			if (productListTmp.size() == 0) {
//				try {
//					productList.remove(productOld);
//					productOld.setProductId(1);
//					productOld.setCategoryId(categoryService.getByName(categoryNew).getCategoryId());
//					productOld.setCategoryName(categoryService.getByName(categoryNew).getCategoryName());
//					productListTmp.add(productOld);
//					return true;
//				} catch (Exception e) {
//					return false;
//				}
//			} else {
//				for (Product product : productListTmp) {
//					if (product.getProductName().equalsIgnoreCase(productOld.getProductName())){
//						return false;
//					} else {
//						try {
//							productList.remove(productOld);
//							productOld.setProductId(productListTmp.get(productListTmp.size() - 1).getProductId() + 1);
//							productOld.setCategoryId(categoryService.getByName(categoryNew).getCategoryId());
//							productOld.setCategoryName(categoryService.getByName(categoryNew).getCategoryName());
//							productListTmp.add(productOld);
//							return true;
//						} catch (Exception e) {
//							return false;
//						}
//					}
//				}
//			}
//			return false;
//		}

	public Product getByName(String productName) {

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getProductName().equalsIgnoreCase(productName)) {
				return productList.get(i);
			}
		}

		return null;
	}

	public List<Product> getByCategoryId(int categoryId) {

		List<Product> productListTmp = new ArrayList<>();

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getCategoryId() == categoryId) {
				productListTmp.add(productList.get(i));
			}
		}

		return productListTmp;
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
