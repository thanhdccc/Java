package com.fabbi.app;
import com.fabbi.business.ClazzManagement;
import com.fabbi.business.StudentManagement;
import com.fabbi.service.ClazzServiceImpl;
import com.fabbi.service.StudentServiceImpl;
import com.fabbi.util.Helper;

public class Main {

	private static Helper helper = null;
	private static ClazzServiceImpl classService = null;
	private static ClazzManagement classHelper = null;
	private static StudentServiceImpl studentService = null;
	private static StudentManagement studentHelper = null;

	public static void main(String[] args) {

		helper = Helper.getInstance();
		
		classService = ClazzServiceImpl.getInstance();
		studentService = StudentServiceImpl.getInstance();
		
		classHelper = ClazzManagement.getInstance();
		studentHelper = StudentManagement.getInstance();
		
		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>>------ Menu Chinh ------<<<<<<");
			helper.mainMenu();
			option = helper.inputOption(1, 9);

			switch (option) {
			case 1:
				classHelper.classManagementExecute(classService, studentService);
				break;
			case 2:
				studentHelper.studentManagementExecute(classService, studentService);
				break;
			case 3:
				studentHelper.searchStudentByName(studentService);
				break;
			case 4:
				studentHelper.searchStudentByDOB(studentService);
				break;
			case 5:
				studentHelper.searchStudentByYear(studentService);
				break;
			case 6:
				studentHelper.sortByName(studentService);
				break;
			case 7:
				classHelper.sortByStudentQuantity(classService);
				break;
			case 8:
				classHelper.statisticByGender(studentService, classService);
				break;
			case 9:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

}
