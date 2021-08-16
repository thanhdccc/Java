package com.fabbi.entity;

import java.util.Date;

public class Member {
	
	private int id;
	private String username;
	private String password;
	private String name;
	private Date dob;
	private String email;
	private String phone;
	private String address;
	
	public Member() {
		
	}
	
	public Member(String username, String password, String name, Date dob, String email, String phone, String address) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public Member(int id, String username, String password, String name, Date dob, String email, String phone,
			String address) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
