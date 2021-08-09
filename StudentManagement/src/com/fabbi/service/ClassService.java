package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.util.DBUtil;
import com.fabbi.entity.Class;

public class ClassService {

	private DBUtil dbUtil = null;
	private List<Class> classList = new ArrayList<>();

	public List<Class> getAll() {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		int id = 0;
		String name = null;
		Class classTmp = null;
		classList.clear();

		try {
			con = dbUtil.getConnection();
			sql = "select class_id, class_name from classes";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);

				classTmp = new Class(id, name);
				classList.add(classTmp);
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
		return classList;
	}

	public boolean add(String name) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();

			sql = "insert into classes (class_name) values (?)";
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

	public boolean update(Class classUpdate) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;
		int id = classUpdate.getId();
		String name = classUpdate.getName();

		try {
			con = dbUtil.getConnection();
			sql = "update classes set class_name = ? where class_id = ?";

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

	public Class getByName(String className) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		Class classTmp = null;
		int id = 0;
		String name = null;

		try {
			con = dbUtil.getConnection();
			sql = "select class_id, class_name from classes where class_name = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, className);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}

			classTmp = new Class(id, name);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classTmp;
	}

	public Class getById(int classId) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		Class classTmp = null;
		int id = 0;
		String name = null;

		try {
			con = dbUtil.getConnection();
			sql = "select class_id, class_name from classes where class_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, classId);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}

			classTmp = new Class(id, name);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return classTmp;
	}

	public boolean delete(String name) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "delete from classes where class_name = ?";

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

	public List<Class> sortByTotalStudent() {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		int id = 0;
		String name = null;
		int total = 0;
		Class classTmp = null;
		List<Class> classList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "select count(a.student_id) as total_student, a.class_id, "
					+ "b.class_name from students as a inner join classes as b "
					+ "on a.class_id = b.class_id group by a.class_id, b.class_name " + "order by total_student desc";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				total = result.getInt(1);
				id = result.getInt(2);
				name = result.getString(3);

				classTmp = new Class(id, name, total);
				classList.add(classTmp);
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
		return classList;
	}
}
