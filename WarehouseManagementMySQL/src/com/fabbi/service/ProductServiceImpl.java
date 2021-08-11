package com.fabbi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Product;
import com.fabbi.util.DBUtil;

public class ProductServiceImpl implements CRUDService<Product>{
	
	private DBUtil dbUtil = null;
	private static ProductServiceImpl instance;
	
	private ProductServiceImpl() {
		
	}
	
	public static ProductServiceImpl getInstance() {
		if (instance == null) {
			instance = new ProductServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Product> getAll() {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		List<Product> productList = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products";
			
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				cate_id = result.getInt(5);
				
				product = new Product(id, name, price, quantity, cate_id);
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
	public boolean add(Product product) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		String name = product.getProductName();
		float price = product.getProductPrice();
		int quantity = product.getProductQuantity();
		int categoryId = product.getCategoryId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO products (name, price, quantity, category_id) VALUES (?, ?, ?, ?)";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setFloat(2, price);
			statement.setInt(3, quantity);
			statement.setInt(4, categoryId);
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

	@Override
	public boolean update(Product productUpdate) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		int id = productUpdate.getProductId();
		String name = productUpdate.getProductName();
		float price = productUpdate.getProductPrice();
		int quantity = productUpdate.getProductQuantity();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setFloat(2, price);
			statement.setInt(3, quantity);
			statement.setInt(4, id);
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
	public Product getByName(String productName) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE name = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, productName);
			result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				cate_id = result.getInt(5);
			}
			
			product = new Product(id, name, price, quantity, cate_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return product;
	}

	public List<Product> getByCategoryId(int categoryId) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		List<Product> productListTmp = new ArrayList<>();
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE category_id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, categoryId);
			result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				cate_id = result.getInt(5);
				
				product = new Product(id, name, price, quantity, cate_id);
				productListTmp.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return productListTmp;
	}

	@Override
	public boolean delete(Product product) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		int id = product.getProductId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM products WHERE id = ?";
			
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
	public Product getById(int productId) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, productId);
			result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				cate_id = result.getInt(5);
			}
			
			product = new Product(id, name, price, quantity, cate_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return product;
	}
}
