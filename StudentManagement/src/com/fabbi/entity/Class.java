package com.fabbi.entity;

public class Class {
	
	private int id;
	private String name;
	private int totalStudent;
	private int totalMale;
	private int totalFemale;
	
	public Class() {
		
	}
	public Class(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Class(int id, String name, int totalStudent) {
		this.id = id;
		this.name = name;
		this.totalStudent = totalStudent;
	}
	
	public Class(int id, String name, int totalMale, int totalFemale) {
		this.id = id;
		this.name = name;
		this.totalMale = totalMale;
		this.totalFemale = totalFemale;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getTotalStudent() {
		return totalStudent;
	}
	
	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}
	
	public int getTotalMale() {
		return totalMale;
	}
	
	public void setTotalMale(int totalMale) {
		this.totalMale = totalMale;
	}
	
	public int getTotalFemale() {
		return totalFemale;
	}
	
	public void setTotalFemale(int totalFemale) {
		this.totalFemale = totalFemale;
	}
	
	public String toString() {
		return String.format("ID lop: %d - Ten lop: %s", id, name);
	}
}
