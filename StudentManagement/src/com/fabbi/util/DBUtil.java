package com.fabbi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/studentdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "gaiaknight";
	private static DBUtil instance;

	private DBUtil() {
	}

	public static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

			if (con != null) {

				return con;
			}
		} catch (SQLException e) {
			System.out.println("Connect to database failed!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (st != null && !st.isClosed()) {
				st.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("Close connection failed!");
			e.printStackTrace();
		}
	}
}
