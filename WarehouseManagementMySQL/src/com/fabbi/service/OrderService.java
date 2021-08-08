package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Order;
import com.fabbi.entity.Product;
import com.fabbi.util.DBUtil;

public class OrderService {

	private DBUtil dbUtil = null;
	private List<Order> orderList = new ArrayList<>();

	public List<Order> getAll() {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		orderList.clear();
		Connection con = null;
		String sql = null;
		Order order = null;
		int id = 0;
		String name = null;
		List<Product> productList = null;
		float totalPrice = 0;

		try {
			con = dbUtil.getConnection();
			sql = "select order_id, order_name, order_price from orders";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				totalPrice = result.getFloat(3);
				productList = getProductListByOrderId(id);

				order = new Order(id, name, totalPrice, productList);
				orderList.add(order);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return orderList;
	}

	public List<Product> getProductListByOrderId(int orderId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		List<Product> productList = new ArrayList<>();
		Product product = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;

		try {
			con = dbUtil.getConnection();
			sql = "select a.order_id, a.product_id, b.product_name, b.product_price, a.product_quantity, b.category_id "
					+ "from product_order as a inner join products as b on a.product_id = b.product_id where order_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(2);
				name = result.getString(3);
				price = result.getFloat(4);
				quantity = result.getInt(5);
				cate_id = result.getInt(6);

				product = new Product(id, name, price, quantity, cate_id);
				productList.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	public boolean addOrder(String name) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "insert into orders (order_name) values (?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean updateOrderTotalPrice(String name, float price) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "update orders set order_price = ? where order_name = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setFloat(1, price);
			statement.setString(2, name);
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean addProductToOrder(int orderId, Product product) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "insert into product_order (order_id, product_id, product_quantity, product_price) values (?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			statement.setInt(2, product.getProductId());
			statement.setInt(3, product.getProductQuantity());
			statement.setFloat(4, product.getProductPrice());
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean updateOrderProductQuantity(int quantity, int orderId, int productId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "update product_order set product_quantity = ? where order_id = ? and product_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, quantity);
			statement.setInt(2, orderId);
			statement.setInt(3, productId);
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Order getOrderByName(String orderName) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		Order order = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select order_id, order_name, order_price from orders where order_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, orderName);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				float price = result.getFloat(3);
				if (result.wasNull()) {
					price = 0;
				}
				
				order = new Order(id, name, price);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return order;
	}
	
	public Product getProductByOrderId(int orderId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select a.product_id, b.product_name from product_order as a "
					+ "inner join products as b on a.product_id = b.product_id where order_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				
				product = new Product(id, name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}
	
	public Product getProductByOrderIdAndProductName(int orderId, String productName) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select a.product_id, b.product_name, a.product_quantity from product_order as a "
					+ "inner join products as b on a.product_id = b.product_id where order_id = ? and b.product_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			statement.setString(2, productName);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int quantity = result.getInt(3);
				
				product = new Product(id, name, quantity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}

	public float totalPrice(List<Product> productList) {

		float total = 0;

		for (Product product : productList) {
			total += (product.getProductPrice() * product.getProductQuantity());
		}

		return total;
	}

	public boolean updateOrderName(Order order) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "update orders set order_name = ? where order_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, order.getOrderName());
			statement.setInt(2, order.getOrderId());
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean deleteProductInOrder(int orderId) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "delete from product_order where order_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean deleteOrder(String orderName) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "delete from orders where order_name = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, orderName);
			statement.executeUpdate();
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
