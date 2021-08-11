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
	private Connection con = null;
	private Statement statement = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet result = null;
	private String sql = null;
	private int id = 0;
	private String rollnumber = null;
	private String name = null;
	private Date dob = null;
	private String gender = null;
	private String address = null;
	private String hobby = null;
	private int classId = 0;
	private Student student = null;

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
		List<Student> studentListTmp = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE class_id = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, idOfClass);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return studentListTmp;
	}

	public boolean add(Student student) {

		dbUtil = DBUtil.getInstance();
		rollnumber = student.getRollnumber();
		name = student.getName();
		dob = new Date(student.getDob().getTime());
		gender = student.getGender();
		address = student.getAddress();
		hobby = student.getHobby();
		classId = student.getClassId();
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO students (rollnumber, name, dob, gender, address, "
					+ "hobby, class_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, rollnumber);
			prepareStatement.setString(2, name);
			prepareStatement.setDate(3, dob);
			prepareStatement.setString(4, gender);
			prepareStatement.setString(5, address);
			prepareStatement.setString(6, hobby);
			prepareStatement.setInt(7, classId);
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

	public boolean update(Student student) {

		dbUtil = DBUtil.getInstance();
		id = student.getId();
		name = student.getName();
		dob = new Date(student.getDob().getTime());
		gender = student.getGender();
		address = student.getAddress();
		hobby = student.getHobby();
		classId = student.getClassId();
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "UPDATE students SET name = ?, dob = ?, gender = ?, "
					+ "address = ?, hobby = ?, class_id = ? WHERE id = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, name);
			prepareStatement.setDate(2, dob);
			prepareStatement.setString(3, gender);
			prepareStatement.setString(4, address);
			prepareStatement.setString(5, hobby);
			prepareStatement.setInt(6, classId);
			prepareStatement.setInt(7, id);
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

	public Student getByRollnumber(String rollnumber) {

		dbUtil = DBUtil.getInstance();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, name, dob, gender, address, hobby, class_id FROM students WHERE rollnumber = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, rollnumber);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return student;
	}

	public List<Student> getListStudentByName(String studentName) {

		dbUtil = DBUtil.getInstance();
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE name LIKE ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, "%" + studentName + "%");
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return studentList;
	}

	public List<Student> getByDOB(Date date) {

		dbUtil = DBUtil.getInstance();
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students WHERE dob = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setDate(1, date);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return studentList;
	}

	public List<Student> getByYear(int year) {

		dbUtil = DBUtil.getInstance();
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE YEAR(dob) = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, year);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return studentList;
	}

	public List<Student> getStudentListSortByName() {

		dbUtil = DBUtil.getInstance();
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
		List<Student> studentList = new ArrayList<>();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, "
					+ "address, hobby, class_id FROM students WHERE gender = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, genderSearch);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return studentList;
	}

	public boolean delete(Student student) {

		dbUtil = DBUtil.getInstance();
		boolean result = false;
		id = student.getId();

		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM students WHERE id = ?";

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
	public Student getById(int studentId) {

		dbUtil = DBUtil.getInstance();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, hobby, class_id FROM students WHERE id = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, studentId);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return student;
	}

	@Override
	public Student getByName(String studentName) {

		dbUtil = DBUtil.getInstance();

		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, rollnumber, name, dob, gender, address, "
					+ "hobby, class_id FROM students WHERE name = ?";

			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, studentName);
			result = prepareStatement.executeQuery();

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
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return student;
	}
}
