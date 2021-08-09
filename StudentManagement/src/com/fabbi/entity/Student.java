package com.fabbi.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
	
	private int id;
	private String rollnumber;
	private String name;
	private Date dob;
	private String gender;
	private String address;
	private String hobby;
	private int classId;
	
	public Student() {
		
	}
	
	public Student(int id, String rollnumber, String name, Date dob, String gender, String address, String hobby, int classId) {
		this.id = id;
		this.rollnumber = rollnumber;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.hobby = hobby;
		this.classId = classId;
	}
	
	public Student(String rollnumber, String name, Date dob, String gender, String address, String hobby, int classId) {
		this.rollnumber = rollnumber;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.hobby = hobby;
		this.classId = classId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
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
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "ID hoc sinh: " + id + " - Ma so: " + rollnumber + " - Ten: " + name + 
				" - Ngay sinh: " + dateFormat.format(dob) + " - Gioi tinh: " + gender + 
				" - Dia chi: " + address + " - So thich: " + hobby + 
				" - ID lop: " + classId;
	}
}
