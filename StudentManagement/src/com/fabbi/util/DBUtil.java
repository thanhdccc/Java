package com.fabbi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/studentdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "gaiaknight";
	
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
}
