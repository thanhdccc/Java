package com.fabbi.service;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Order;
import com.fabbi.entity.Product;

public class OrderService {

	private List<Order> orderList = new ArrayList<>();

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Order> getAll() {
		return orderList;
	}

	public boolean add(String name, float totalPrice, List<Product> productList) {
		int orderId;

		if (orderList.size() == 0) {
			orderId = 1;
		} else {
			orderId = orderList.get(orderList.size() - 1).getOrderId() + 1;
		}

		try {
			Order order = new Order(orderId, name, totalPrice, productList);
			orderList.add(order);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Order getByName(String orderName) {

		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderName().equals(orderName)) {
				return orderList.get(i);
			}
		}

		return null;
	}

	public Product getOrderProductByName(String orderName, String productName) {

		List<Product> productList = getByName(orderName).getProductList();

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getProductName().equals(productName)) {
				return productList.get(i);
			}
		}

		return null;
	}

	public float totalPrice(List<Product> productList) {

		float total = 0;

		for (Product product : productList) {
			total += (product.getProductPrice() * product.getProductQuantity());
		}

		return total;
	}

	public boolean update(String orderNameOld, String orderNameNew, String productName, int quantityNew, int option) {
		try {
			if (option == 1) {
				getByName(orderNameOld).setOrderName(orderNameNew);
			}
			if (option == 2) {
				List<Product> productList = getByName(orderNameOld).getProductList();
				for (Product product : productList) {
					if (product.getProductName().equalsIgnoreCase(productName)) {
						product.setProductQuantity(quantityNew);
						break;
					}
				}
				getByName(orderNameOld).setTotalPrice(totalPrice(productList));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean delete(String orderName) {
		try {
			orderList.remove(getByName(orderName));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
