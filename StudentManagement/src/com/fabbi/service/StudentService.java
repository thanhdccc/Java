package com.fabbi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.fabbi.entity.Student;
import com.fabbi.util.DBUtil;

public class StudentService {

	private DBUtil dbUtil = null;
	private List<Student> studentList = new ArrayList<>();

	public List<Student> getAll() {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, "
					+ "student_address, student_hobby, class_id from students";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}

	public List<Student> getByClassId(int idOfClass) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, student_address, "
					+ "student_hobby, class_id from students where class_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, idOfClass);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentListTmp;
	}

	public boolean add(Student student) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "insert into students (rollnumber, student_name, dob, gender, student_address, student_hobby, class_id) "
					+ "values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, rollnumber);
			statement.setString(2, name);
			statement.setDate(3, dob);
			statement.setString(4, gender);
			statement.setString(5, address);
			statement.setString(6, hobby);
			statement.setInt(7, classId);
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

	public boolean update(Student student) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "update students set student_name = ?, dob = ?, gender = ?, "
					+ "student_address = ?, student_hobby = ?, class_id = ? where rollnumber = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, name);
			statement.setDate(2, dob);
			statement.setString(3, gender);
			statement.setString(4, address);
			statement.setString(5, hobby);
			statement.setInt(6, classId);
			statement.setString(7, rollnumber);
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

	public Student getByRollnumber(String rollnumber) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, student_name, dob, gender, student_address, "
					+ "student_hobby, class_id from students where rollnumber = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, rollnumber);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;
	}

	public List<Student> getByName(String studentName) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, student_address, "
					+ "student_hobby, class_id from students where student_name = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, studentName);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}

	public List<Student> getByDOB(Date date) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, student_address, "
					+ "student_hobby, class_id from students where dob = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, date);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}

	public List<Student> getByYear(int year) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, student_address, "
					+ "student_hobby, class_id from students where year(dob) = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, year);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}

	public List<Student> getStudentListSortByName() {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, "
					+ "student_address, student_hobby, class_id from students order by student_name";

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}
	
	public List<Student> getStudentListByGender(String genderSearch) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
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
			sql = "select student_id, rollnumber, student_name, dob, gender, "
					+ "student_address, student_hobby, class_id from students where gender = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, genderSearch);
			ResultSet result = statement.executeQuery();

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
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return studentList;
	}

	public boolean delete(String rollnumber) {

		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}

		Connection con = null;
		String sql = null;
		boolean result = false;

		try {
			con = dbUtil.getConnection();
			sql = "delete from students where rollnumber = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, rollnumber);
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
