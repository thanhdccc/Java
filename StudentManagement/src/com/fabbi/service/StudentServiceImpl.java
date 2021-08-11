package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.fabbi.entity.Student;
import com.fabbi.util.DBUtil;

public class StudentServiceImpl implements CRUDService<Student> {

	private DBUtil dbUtil = null;
	private static StudentServiceImpl instance;

	private StudentServiceImpl() {

	}

	public static StudentServiceImpl getInstance() {
		if (instance == null) {
			instance = new StudentServiceImpl();
		}
		return instance;
	}

	public List<Student> getAll() {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students";

			statement = con.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return studentList;
	}

	public List<Student> getByClassId(int idOfClass) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		List<Student> studentListTmp = new ArrayList<>();
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE class_id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, idOfClass);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentListTmp.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return studentListTmp;
	}

	public boolean add(Student student) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		String rollnumber = student.getRollnumber();
		String name = student.getName();
		Date dob = new Date(student.getDob().getTime());
		String gender = student.getGender();
		String address = student.getAddress();
		String hobby = student.getHobby();
		int classId = student.getClassId();
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO students (rollnumber, name, dob, gender, address, "
					+ "hobby, class_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, rollnumber);
			statement.setString(2, name);
			statement.setDate(3, dob);
			statement.setString(4, gender);
			statement.setString(5, address);
			statement.setString(6, hobby);
			statement.setInt(7, classId);
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

	public boolean update(Student student) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		int id = student.getId();
		String name = student.getName();
		Date dob = new Date(student.getDob().getTime());
		String gender = student.getGender();
		String address = student.getAddress();
		String hobby = student.getHobby();
		int classId = student.getClassId();
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE students SET name = ?, dob = ?, gender = ?, "
					+ "address = ?, hobby = ?, class_id = ? WHERE id = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setDate(2, dob);
			statement.setString(3, gender);
			statement.setString(4, address);
			statement.setString(5, hobby);
			statement.setInt(6, classId);
			statement.setInt(7, id);
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

	public Student getByRollnumber(String rollnumber) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, dob, gender, address, hobby, class_id FROM students WHERE rollnumber = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, rollnumber);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(2);
				dob = result.getDate(3);
				gender = result.getString(4);
				address = result.getString(5);
				hobby = result.getString(6);
				classId = result.getInt(7);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return student;
	}

	public List<Student> getListStudentByName(String studentName) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE name LIKE ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, "%" + studentName + "%");
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return studentList;
	}

	public List<Student> getByDOB(Date date) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students WHERE dob = ?";

			statement = con.prepareStatement(sql);
			statement.setDate(1, date);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return studentList;
	}

	public List<Student> getByYear(int year) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE YEAR(dob) = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, year);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return studentList;
	}

	public List<Student> getStudentListSortByName() {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students ORDER BY name";

			statement = con.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return studentList;
	}

	public List<Student> getStudentListByGender(String genderSearch) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, "
					+ "address, hobby, class_id FROM students WHERE gender = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, genderSearch);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
				studentList.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return studentList;
	}

	public boolean delete(Student student) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		String sql = null;
		boolean result = false;
		int id = student.getId();

		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM students WHERE id = ?";

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
	public Student getById(int studentId) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students WHERE id = ?";

			statement = con.prepareStatement(sql);
			statement.setInt(1, studentId);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return student;
	}

	@Override
	public Student getByName(String studentName) {

		dbUtil = DBUtil.getInstance();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql = null;
		int id = 0;
		String rollnumber = null;
		String name = null;
		Date dob = null;
		String gender = null;
		String address = null;
		String hobby = null;
		int classId = 0;
		Student student = null;

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE name = ?";

			statement = con.prepareStatement(sql);
			statement.setString(1, studentName);
			result = statement.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				rollnumber = result.getString(2);
				name = result.getString(3);
				dob = result.getDate(4);
				gender = result.getString(5);
				address = result.getString(6);
				hobby = result.getString(7);
				classId = result.getInt(8);

				student = new Student(id, rollnumber, name, dob, gender, address, hobby, classId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, statement, null, result);
		}
		return student;
	}
}
