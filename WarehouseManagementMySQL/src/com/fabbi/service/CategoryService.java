package com.fabbi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Category;
import com.fabbi.util.DBUtil;

public class CategoryService {

	private DBUtil dbUtil = null;
	private List<Category> categoryList = new ArrayList<>();

	public List<Category> getAll() {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		int id = 0;
		String name = null;
		Category category = null;
		categoryList.clear();
		
		try {
			con = dbUtil.getConnection();
			sql = "select category_id, category_name from categories";
			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				
				category = new Category(id, name);
				categoryList.add(category);
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
		return categoryList;
	}

	public boolean add(String categoryName) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			
			sql = "insert into categories (category_name) values (?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, categoryName);
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

	public boolean update(Category categoryUpdate) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		int id = categoryUpdate.getCategoryId();
		String name = categoryUpdate.getCategoryName();
		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "update categories set category_name = ? where category_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
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

	public Category getByName(String categoryName) {
		
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		Category category = null;
		int id = 0;
		String name = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select category_id, category_name from categories where category_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, categoryName);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}
			
			category = new Category(id, name);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return category;
	}

	public boolean delete(String categoryNameDelete) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "delete from categories where category_name = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, categoryNameDelete);
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

	public Category getById(int categoryId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		
		Connection con = null;
		String sql = null;
		Category category = null;
		int id = 0;
		String name = null;
		
		try {
			con = dbUtil.getConnection();
			sql = "select category_id, category_name from categories where category_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, categoryId);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}
			
			category = new Category(id, name);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return category;
	}
}
