package com.fabbi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Product;
import com.fabbi.util.DBUtil;

public class ProductService {
	
	private DBUtil dbUtil = null;
	private List<Product> productList = new ArrayList<>();

	public List<Product> getAll() {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		productList.clear();
		
		try {
			con = dbUtil.getConnection();
			sql = "select product_id, product_name, product_price, product_quantity, category_id from products";
			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	public boolean add(String name, float price, int quantity, int categoryId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "insert into products (product_name, product_price, product_quantity, category_id) values (?, ?, ?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setFloat(2, price);
			statement.setInt(3, quantity);
			statement.setInt(4, categoryId);
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

	public boolean update(Product productUpdate) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		int id = productUpdate.getProductId();
		String name = productUpdate.getProductName();
		float price = productUpdate.getProductPrice();
		int quantity = productUpdate.getProductQuantity();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "update products set product_name = ?, product_price = ?, product_quantity = ? where product_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setFloat(2, price);
			statement.setInt(3, quantity);
			statement.setInt(4, id);
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
	
	public boolean updateProductQuantity(Product productUpdate) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		int id = productUpdate.getProductId();
		int quantity = productUpdate.getProductQuantity();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "update products set product_quantity = ? where product_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, quantity);
			statement.setInt(2, id);
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

	public Product getByName(String productName) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		int id = 0;
		String name = null;
		float price = 0;
		int quantity = 0;
		int cate_id = 0;
		Product product = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select product_id, product_name, product_price, product_quantity, category_id from products where product_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productName);
			ResultSet result = statement.executeQuery();
			
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	public List<Product> getByCategoryId(int categoryId) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
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
			sql = "select product_id, product_name, product_price, product_quantity, category_id from products where category_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, categoryId);
			ResultSet result = statement.executeQuery();
			
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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productListTmp;
	}

	public boolean delete(String productNameDelete) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "delete from products where product_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, productNameDelete);
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
