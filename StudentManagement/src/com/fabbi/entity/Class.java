package com.fabbi.entity;

public class Class {
	
	private int id;
	private String name;
	private int totalStudent;
	
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
	
	public String toString() {
		return String.format("Class ID: %d - Class Name: %s", id, name);
	}
}
