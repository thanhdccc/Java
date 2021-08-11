package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.util.DBUtil;
import com.fabbi.entity.Clazz;
import com.fabbi.entity.ClazzDetail;

public class ClazzServiceImpl implements CRUDService<Clazz>{

	private DBUtil dbUtil = null;
	private static ClazzServiceImpl instance;
	private Connection con = null;
	private Statement statement = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet result = null;
	private String sql = null;
	
	private ClazzServiceImpl() {
		
	}
	
	public static ClazzServiceImpl getInstance() {
		if (instance == null) {
			instance = new ClazzServiceImpl();
		}
		return instance;
	}

	public List<Clazz> getAll() {

		dbUtil = DBUtil.getInstance();
		List<Clazz> classList = new ArrayList<>();
		int id = 0;
		String name = null;
		Clazz classTmp = null;
		classList.clear();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM classes";

			statement = con.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);

				classTmp = new Clazz(id, name);
				classList.add(classTmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return classList;
	}

	public boolean add(Clazz clazz) {

		dbUtil = DBUtil.getInstance();
		boolean result = false;
		String name = clazz.getName();

		try {
			con = dbUtil.getConnection();

			sql = "INSERT INTO classes (name) VALUES (?)";
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, name);
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

	public boolean update(Clazz classUpdate) {

		dbUtil = DBUtil.getInstance();
		boolean result = false;
		int id = classUpdate.getId();
		String name = classUpdate.getName();

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE classes SET name = ? WHERE id = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setInt(2, id);
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

	public Clazz getByName(String className) {

		dbUtil = DBUtil.getInstance();
		Clazz classTmp = null;
		int id = 0;
		String name = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM classes WHERE name = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, className);
			result = prepareStatement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}

			classTmp = new Clazz(id, name);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return classTmp;
	}

	public Clazz getById(int classId) {

		dbUtil = DBUtil.getInstance();
		Clazz classTmp = null;
		int id = 0;
		String name = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name FROM classes WHERE id = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, classId);
			result = prepareStatement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
			}

			classTmp = new Clazz(id, name);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return classTmp;
	}

	public boolean delete(Clazz clazz) {

		dbUtil = DBUtil.getInstance();
		boolean result = false;
		int id = clazz.getId();

		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM classes WHERE id = ?";

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

	public List<ClazzDetail> sortByTotalStudent() {

		dbUtil = DBUtil.getInstance();
		int id = 0;
		String name = null;
		int total = 0;
		ClazzDetail classTmp = null;
		List<ClazzDetail> classList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT COUNT(a.id) AS total_student, a.class_id, "
					+ "b.name FROM students AS a INNER JOIN classes AS b "
					+ "ON a.class_id = b.id GROUP BY a.class_id, b.name "
					+ "ORDER BY total_student DESC";

			statement = con.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				total = result.getInt(1);
				id = result.getInt(2);
				name = result.getString(3);

				classTmp = new ClazzDetail(id, name, total);
				classList.add(classTmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return classList;
	}
}
