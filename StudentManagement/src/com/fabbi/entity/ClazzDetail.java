package com.fabbi.entity;

public class ClazzDetail extends Clazz{

	private int totalStudent;
	private int totalMale;
	private int totalFemale;
	
	public ClazzDetail() {
		super();
	}
	
	public ClazzDetail(int id, String name, int totalStudent) {
		super(id, name);
		this.totalStudent = totalStudent;
	}

	public ClazzDetail(int id, String name, int totalMale, int totalFemale) {
		super(id, name);
		this.totalMale = totalMale;
		this.totalFemale = totalFemale;
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
}
