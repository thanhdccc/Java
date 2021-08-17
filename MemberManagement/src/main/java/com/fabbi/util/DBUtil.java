package com.fabbi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/memberdb";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "gaiaknight";
	private static DBUtil instance;
	private static List<Connection> conList = new ArrayList<>();
	private int i = 0;

	private DBUtil() {
		for (int i = 0; i < 5; i++) {
			try {
				Class.forName(JDBC_DRIVER);
				Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);

				if (con != null) {

					conList.add(con);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	public Connection getConnection() {
		Connection con = conList.get(i);
		i++;
		if (i == 4) {
			i = 0;
		}
		return con;
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
			e.printStackTrace();
		}
	}
}
