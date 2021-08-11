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
	private Connection con = null;
	private Statement statement = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet result = null;
	private String sql = null;
	private int id = 0;
	private String name = null;
	private float price = 0;
	private int quantity = 0;
	private int categoryId = 0;
	private Product product = null;
	
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
				categoryId = result.getInt(5);
				
				product = new Product(id, name, price, quantity, categoryId);
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
		name = product.getProductName();
		price = product.getProductPrice();
		quantity = product.getProductQuantity();
		categoryId = product.getCategoryId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO products (name, price, quantity, category_id) VALUES (?, ?, ?, ?)";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setFloat(2, price);
			prepareStatement.setInt(3, quantity);
			prepareStatement.setInt(4, categoryId);
			int resultAdd = prepareStatement.executeUpdate();
			if (resultAdd == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, null);
		}
		return result;
	}

	@Override
	public boolean update(Product productUpdate) {

		dbUtil = DBUtil.getInstance();
		id = productUpdate.getProductId();
		name = productUpdate.getProductName();
		price = productUpdate.getProductPrice();
		quantity = productUpdate.getProductQuantity();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setFloat(2, price);
			prepareStatement.setInt(3, quantity);
			prepareStatement.setInt(4, id);
			int resultUpdate = prepareStatement.executeUpdate();
			if (resultUpdate == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, null);
		}
		return result;
	}

	@Override
	public Product getByName(String productName) {
		
		dbUtil = DBUtil.getInstance();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE name = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, productName);
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				categoryId = result.getInt(5);
			}
			
			product = new Product(id, name, price, quantity, categoryId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return product;
	}

	public List<Product> getByCategoryId(int categoryId) {
		
		dbUtil = DBUtil.getInstance();
		List<Product> productListTmp = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE category_id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, categoryId);
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				categoryId = result.getInt(5);
				
				product = new Product(id, name, price, quantity, categoryId);
				productListTmp.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return productListTmp;
	}

	@Override
	public boolean delete(Product product) {

		dbUtil = DBUtil.getInstance();
		id = product.getProductId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM products WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			int resultDelete = prepareStatement.executeUpdate();
			if (resultDelete == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, null);
		}
		return result;
	}

	@Override
	public Product getById(int productId) {
		
		dbUtil = DBUtil.getInstance();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, price, quantity, category_id FROM products WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, productId);
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				price = result.getFloat(3);
				quantity = result.getInt(4);
				categoryId = result.getInt(5);
			}
			
			product = new Product(id, name, price, quantity, categoryId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return product;
	}
}
