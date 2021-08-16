package com.fabbi.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fabbi.entity.Member;
import com.fabbi.util.DBUtil;

public class MemberServiceImpl implements CRUDService<Member> {
	
	private static MemberServiceImpl instance;
	private DBUtil dbUtil = null;
	private Connection con = null;
	private Statement statement = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet result = null;
	private String sql = null;
	private int id = 0;
	private String username = null;
	private String password = null;
	private String name = null;
	private Date dob = null;
	private String email = null;
	private String address = null;
	private String phone = null;
	private Member member = null;
	
	private MemberServiceImpl() {
		
	}
	
	public static MemberServiceImpl getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}

	@Override
	public boolean add(Member obj) {
		
		dbUtil = DBUtil.getInstance();
		username = obj.getUsername();
		password = obj.getPassword();
		name = obj.getName();
		dob = new Date(obj.getDob().getTime());
		email = obj.getEmail();
		phone = obj.getPhone();
		address = obj.getAddress();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "INSERT INTO members (username, password, name, dob, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			prepareStatement.setString(3, name);
			prepareStatement.setDate(4, dob);
			prepareStatement.setString(5, email);
			prepareStatement.setString(6, phone);
			prepareStatement.setString(7, address);
			int resultSQL = prepareStatement.executeUpdate();
			if(resultSQL == 1) {
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
	public boolean update(Member obj) {

		dbUtil = DBUtil.getInstance();
		id = obj.getId();
		username = obj.getUsername();
		password = obj.getPassword();
		name = obj.getName();
		dob = new Date(obj.getDob().getTime());
		email = obj.getEmail();
		phone = obj.getPhone();
		address = obj.getAddress();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "UPDATE members SET username = ?, password = ?, name = ?, dob = ?, email = ?, phone = ?, address = ? WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			prepareStatement.setString(3, name);
			prepareStatement.setDate(4, dob);
			prepareStatement.setString(5, email);
			prepareStatement.setString(6, phone);
			prepareStatement.setString(7, address);
			prepareStatement.setInt(8, id);
			int resultSQL = prepareStatement.executeUpdate();
			if(resultSQL == 1) {
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
	public boolean delete(Member obj) {

		dbUtil = DBUtil.getInstance();
		id = obj.getId();
		boolean result = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "DELETE FROM members WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			int resultSQL = prepareStatement.executeUpdate();
			if(resultSQL == 1) {
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
	public Member getById(int memberId) {

		dbUtil = DBUtil.getInstance();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, username, password, name, dob, email, phone, address FROM members WHERE id = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setInt(1, memberId);
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				username = result.getString(2);
				password = result.getString(3);
				name = result.getString(4);
				dob = result.getDate(5);
				email = result.getString(6);
				phone = result.getString(7);
				address = result.getString(8);
				
				member = new Member(id, username, password, name, dob, email, phone, address);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return member;
	}

	@Override
	public Member getByName(String memberName) {

		dbUtil = DBUtil.getInstance();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, username, password, name, dob, email, phone, address FROM members WHERE name = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, memberName);
			result = prepareStatement.executeQuery();
			
			while (result.next()) {
				id = result.getInt(1);
				username = result.getString(2);
				password = result.getString(3);
				name = result.getString(4);
				dob = result.getDate(5);
				email = result.getString(6);
				phone = result.getString(7);
				address = result.getString(8);
				
				member = new Member(id, username, password, name, dob, email, phone, address);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return member;
	}

	@Override
	public List<Member> getAll() {
		
		dbUtil = DBUtil.getInstance();
		List<Member> memberList = new ArrayList<>();
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, username, password, name, dob, email, phone, address FROM members";
			
			statement = con.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {
				id = result.getInt(1);
				username = result.getString(2);
				password = result.getString(3);
				name = result.getString(4);
				dob = result.getDate(5);
				email = result.getString(6);
				phone = result.getString(7);
				address = result.getString(8);
				
				member = new Member(id, username, password, name, dob, email, phone, address);
				memberList.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, null, statement, result);
		}
		return memberList;
	}

//	public boolean getByUsername(String memberUsername) {
//
//		dbUtil = DBUtil.getInstance();
//		boolean resultGet = false;
//		
//		try {
//			con = dbUtil.getConnection();
//			sql = "SELECT id, username FROM members WHERE username = ?";
//			
//			prepareStatement = con.prepareStatement(sql);
//			prepareStatement.setString(1, memberUsername);
//			result = prepareStatement.executeQuery();
//			
//			resultGet = result.next();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			dbUtil.closeConnection(con, prepareStatement, null, result);
//		}
//		return resultGet;
//	}
	
	public boolean getByUsernameAndPassword(String memberUsername, String memberPassword) {

		dbUtil = DBUtil.getInstance();
		boolean resultLogin = false;
		
		try {
			con = dbUtil.getConnection();
			sql = "SELECT id, username, password FROM members WHERE username = ? AND password = ?";
			
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, memberUsername);
			prepareStatement.setString(2, memberPassword);
			result = prepareStatement.executeQuery();

			resultLogin = result.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection(con, prepareStatement, null, result);
		}
		return resultLogin;
	}
}
