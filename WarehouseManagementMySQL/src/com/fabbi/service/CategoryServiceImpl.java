package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Category;
import com.fabbi.entity.CategoryDetail;
import com.fabbi.util.DBUtil;

public class CategoryServiceImpl implements CRUDService<Category>{

	private DBUtil dbUtil = null;
	private static CategoryServiceImpl instance;
	
	private CategoryServiceImpl() {
		
	}
	
	public static CategoryServiceImpl getInstance() {
		if (instance == null) {
			instance = new CategoryServiceImpl();
		}
		return instance;
	}
	
	@Override
	public boolean add(Category obj) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		String categoryName = obj.getCategoryName();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			
			sql = "INSERT INTO categories (name) VALUES (?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, categoryName);
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
	public boolean update(Category obj) {
		
		dbUtil = DBUtil.getInstance();
		int id = obj.getCategoryId();
		String name = obj.getCategoryName();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "UPDATE categories SET name = ? WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, id);
			int resultUpdate = statement.executeUpdate();
			if (resultUpdate == 1)  {
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
	public boolean delete(Category obj) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		int id = obj.getCategoryId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM categories WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, null);
		}
		return result;
	}

	@Override
	public Category getById(int categoryId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Category category = null;
		int id = 0;
		String name = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM categories WHERE id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setInt(1, categoryId);
			result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}
			
			category = new Category(id, name);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}

		return category;
	}

	@Override
	public List<Category> getAll() {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String name = null;
		Category category = null;
		List<Category> categoryList = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM categories";
			
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				
				category = new Category(id, name);
				categoryList.add(category);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return categoryList;
	}

	@Override
	public Category getByName(String categoryName) {
		
		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		Category category = null;
		int id = 0;
		String name = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM categories WHERE name = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, categoryName);
			result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}
			
			category = new Category(id, name);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}

		return category;
	}
	
	public List<CategoryDetail> totalProductOfCategory() {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		int numberOfProduct = 0;
		String name = null;
		List<CategoryDetail> list = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT COUNT(a.id) as number_product, b.id, b.name FROM products AS a "
					+ "INNER JOIN categories AS b ON a.category_id = b.id "
					+ "GROUP BY b.id, b.name";
			
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				numberOfProduct = result.getInt(1);
				id = result.getInt(2);
				name = result.getString(3);
				
				CategoryDetail detail = new CategoryDetail(id, name, numberOfProduct);
				list.add(detail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return list;
	}

}
