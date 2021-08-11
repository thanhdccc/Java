package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Order;
import com.fabbi.entity.Product;
import com.fabbi.util.DBUtil;

public class OrderServiceImpl implements CRUDService<Order>{

	private DBUtil dbUtil = null;
	private static OrderServiceImpl instance;
	
	private OrderServiceImpl() {
		
	}
	
	public static OrderServiceImpl getInstance() {
		if (instance == null) {
			instance = new OrderServiceImpl();
		}
		return instance;
	}
	
	public List<Product> top10BestSellerProduct() {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		List<Product> productList = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT SUM(a.product_quantity) as total_quantity, a.product_id, b.name "
					+ "FROM orderdetail AS a INNER JOIN products AS b ON a.product_id = b.id "
					+ "GROUP BY a.product_id, b.name ORDER BY total_quantity DESC LIMIT 10";
			
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				int quantity = result.getInt(1);
				int id = result.getInt(2);
				String name = result.getString(3);
				
				Product product = new Product(id, name, quantity);
				productList.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return productList;
	}

	@Override
	public List<Order> getAll() {

		dbUtil = DBUtil.getInstance();
		List<Order> orderList = new ArrayList<>();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		Order order = null;
		int id = 0;
		String name = null;
		List<Product> productList = null;
		float totalPrice = 0;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price FROM orders";

			statement = con.createStatement();
			result = statement.executeQuery(sql);

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
			dbUtil.closeConnection(con, null, statement, result);
		}

		return orderList;
	}

	public List<Product> getProductListByOrderId(int orderId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		List<Product> productList = new ArrayList<>();
		Product product = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cateId = 0;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT a.order_id, a.product_id, b.name, a.product_price, a.product_quantity, b.category_id "
					+ "FROM orderdetail AS a INNER JOIN products AS b ON a.product_id = b.id WHERE a.order_id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(2);
				name = result.getString(3);
				price = result.getFloat(4);
				quantity = result.getInt(5);
				cateId = result.getInt(6);

				product = new Product(id, name, price, quantity, cateId);
				productList.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		
		return productList;
	}

	@Override
	public boolean add(Order order) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO orders (name) VALUES (?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, order.getOrderName());
			int resultAdd = statement.executeUpdate();
			if (resultAdd == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}

	public boolean updateOrderTotalPrice(String name, float price) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE orders SET price = ? WHERE name = ?";

			statement = con.prepareStatement(sql);
			statement.setFloat(1, price);
			statement.setString(2, name);
			int resultUpdate = statement.executeUpdate();
			if (resultUpdate == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}
	
	public boolean addProductToOrder(int orderId, Product product) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO orderdetail (order_id, product_id, product_quantity, product_price) VALUES (?, ?, ?, ?)";

			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			statement.setInt(2, product.getProductId());
			statement.setInt(3, product.getProductQuantity());
			statement.setFloat(4, product.getProductPrice());
			int resultAdd = statement.executeUpdate();
			if (resultAdd == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}
	
	public boolean updateOrderProductQuantity(int quantity, int orderId, int productId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE orderdetail SET product_quantity = ? WHERE order_id = ? AND product_id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, quantity);
			statement.setInt(2, orderId);
			statement.setInt(3, productId);
			int resultUpdate = statement.executeUpdate();
			if (resultUpdate == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}

	@Override
	public Order getByName(String orderName) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Order order = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price FROM orders WHERE name = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, orderName);
			result = statement.executeQuery();
			
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
			dbUtil.closeConnection(con, statement, null, result);
		}

		return order;
	}
	
	public Product getProductByOrderId(int orderId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT a.product_id, b.name FROM orderdetail AS a "
					+ "INNER JOIN products AS b ON a.product_id = b.id WHERE order_id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				
				product = new Product(id, name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}

		return product;
	}
	
	public Product getProductByOrderIdAndProductName(int orderId, String productName) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT a.product_id, b.name, a.product_quantity FROM orderdetail AS a "
					+ "INNER JOIN products AS b ON a.product_id = b.id where a.order_id = ? AND b.name = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			statement.setString(2, productName);
			result = statement.executeQuery();
			
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int quantity = result.getInt(3);
				
				product = new Product(id, name, quantity);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
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

	@Override
	public boolean update(Order order) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE orders SET name = ? WHERE id = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, order.getOrderName());
			statement.setInt(2, order.getOrderId());
			int resultUpdate = statement.executeUpdate();
			if (resultUpdate == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}
	
	public boolean deleteProductInOrder(int orderId) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM orderdetail WHERE order_id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			int resultDelete = statement.executeUpdate();
			if (resultDelete == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}

	@Override
	public boolean delete(Order order) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		int id = order.getOrderId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM orders WHERE id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			int resultDelete = statement.executeUpdate();
			if (resultDelete == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		
		return result;
	}

	@Override
	public Order getById(int orderId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Order order = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price FROM orders WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, orderId);
			result = statement.executeQuery();
			
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
			dbUtil.closeConnection(con, statement, null, result);
		}

		return order;
	}
}
