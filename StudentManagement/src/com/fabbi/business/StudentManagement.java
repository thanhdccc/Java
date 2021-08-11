package com.fabbi.business;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fabbi.entity.Student;
import com.fabbi.entity.Clazz;
import com.fabbi.service.ClazzServiceImpl;
import com.fabbi.service.StudentServiceImpl;
import com.fabbi.util.Helper;

public class StudentManagement {

	private Helper helper = null;
	private static StudentManagement instance;
	
	private StudentManagement() {
		
	}
	
	public static StudentManagement getInstance() {
		if (instance == null) {
			instance = new StudentManagement();
		}
		return instance;
	}

	@SuppressWarnings("resource")
	public void searchStudentByName(StudentServiceImpl studentService) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Nhap ten hoc sinh: ");
		String name = scanner.nextLine();
		List<Student> studentList = studentService.getListStudentByName(name);
		if (studentList.size() == 0) {
			System.out.printf("Khong tim thay hoc sinh ten \"%s\"!", name);
			System.out.println();
		} else {
			studentList.forEach(System.out::println);
		}
	}

	public void searchStudentByDOB(StudentServiceImpl studentService) {

		helper = Helper.getInstance();
		
		Date date = new Date(helper.inputDOB().getTime());

		List<Student> studentList = studentService.getByDOB(date);
		if (studentList.size() == 0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Khong tim thay hoc sinh co ngay thang nam sinh \"" + dateFormat.format(date) + "\" !");
		} else {
			studentList.forEach(System.out::println);
		}
	}

	public void sortByName(StudentServiceImpl studentService) {

		List<Student> studentList = studentService.getStudentListSortByName();
		if (studentList.size() == 0) {
			System.out.println("Khong co hoc sinh nao!");
		} else {
			studentList.forEach(System.out::println);
		}
	}

	public void searchStudentByYear(StudentServiceImpl studentService) {

		int year = 2010;
		List<Student> studentList = studentService.getByYear(year);
		if (studentList.size() == 0) {
			System.out.printf("Khong tim thay hoc sinh co nam sinh %d", year);
			System.out.println();
		} else {
			studentList.forEach(System.out::println);
		}
	}

	@SuppressWarnings("resource")
	public void studentUpdateManagement(String rollnumber, StudentServiceImpl studentService, ClazzServiceImpl classService) {

		helper = Helper.getInstance();

		Scanner scanner = new Scanner(System.in);
		int option = 0;
		boolean checkOption = true;
		Student student = null;

		do {
			System.out.println();
			System.out.printf(">>>>>> Sua thong tin hoc sinh ma so: %s <<<<<<", rollnumber);
			System.out.println();
			helper.studentUpdateMenu();
			option = helper.inputOption(1, 7);

			switch (option) {
			case 1:
				student = studentService.getByRollnumber(rollnumber);
				System.out.print("Nhap ten moi: ");
				String name = scanner.nextLine();
				student.setName(name);
				boolean resultName = studentService.update(student);

				if (resultName) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 2:
				student = studentService.getByRollnumber(rollnumber);
				Date date = new Date(helper.inputDOB().getTime());
				student.setDob(date);
				boolean resultDOB = studentService.update(student);

				if (resultDOB) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 3:
				student = studentService.getByRollnumber(rollnumber);
				String gender = helper.inputGender();
				student.setGender(gender);
				boolean resultGender = studentService.update(student);

				if (resultGender) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 4:
				student = studentService.getByRollnumber(rollnumber);
				System.out.print("Nhap dia chi moi: ");
				String address = scanner.nextLine();
				student.setAddress(address);
				boolean resultAddress = studentService.update(student);

				if (resultAddress) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 5:
				student = studentService.getByRollnumber(rollnumber);
				System.out.print("Nhap so thich moi: ");
				String hobby = scanner.nextLine();
				student.setHobby(hobby);
				boolean resultHobby = studentService.update(student);

				if (resultHobby) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 6:
				student = studentService.getByRollnumber(rollnumber);
				List<Clazz> classList = classService.getAll();
				String className = helper.inputClassName(classList, 1, 1);
				Clazz classTmp = classService.getByName(className);
				student.setClassId(classTmp.getId());
				boolean resultChangeClass = studentService.update(student);

				if (resultChangeClass) {
					System.out.println("Sua thanh cong!");
				} else {
					System.out.println("Sua that bai!");
				}
				break;
			case 7:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

	@SuppressWarnings("resource")
	public void studentManagementExecute(ClazzServiceImpl classService, StudentServiceImpl studentService) {

		helper = Helper.getInstance();

		Scanner scanner = new Scanner(System.in);
		int option = 0;
		boolean checkOption = true;
		List<Student> studentList = null;
		List<Clazz> classList = null;
		Student studentTmp = null;
		Clazz classTmp = null;

		do {
			System.out.println();
			System.out.println(">>>>>> Quan ly hoc sinh <<<<<<");
			helper.subMenu("hoc sinh");
			option = helper.inputOption(1, 5);

			switch (option) {
			case 1:
				if (classList != null) {
					classList.clear();
				}
				if (studentList != null) {
					studentList.clear();
				}
				classList = classService.getAll();
				studentList = studentService.getAll();
				List<Student> matching = null;

				if (classList.size() == 0) {
					System.out.println("Khong co lop hoc nao");
				} else {
					for (Clazz classObj : classList) {
						System.out.printf("------> %s <------", classObj.getName());
						System.out.println();

						if (studentList.size() == 0) {
							System.out.println("Khong co hoc sinh nao!");
						} else {
							matching = studentList.stream().filter(s -> s.getClassId() == classObj.getId())
									.collect(Collectors.toList());
							matching.forEach(System.out::println);
							matching.clear();
						}
					}
				}
				break;
			case 2:
				if (classList != null) {
					classList.clear();
				}
				if (studentList != null) {
					studentList.clear();
				}
				classList = classService.getAll();
				if (classList.size() == 0) {
					System.out.println("Khong co lop hoc nao");
				} else {
					studentList = studentService.getAll();
					String className = helper.inputClassName(classList, 0, 1);
					classTmp = classService.getByName(className);
					String rollnumber = helper.inputRollnumber(studentList, 0);
					System.out.print("Nhap ten hoc sinh: ");
					String name = scanner.nextLine();
					Date dob = new Date(helper.inputDOB().getTime());
					String gender = helper.inputGender();
					System.out.print("Nhap dia chi: ");
					String address = scanner.nextLine();
					System.out.print("Nhap so thich: ");
					String hobby = scanner.nextLine();
					studentTmp = new Student(rollnumber, name, dob, gender, address, hobby, classTmp.getId());

					boolean resultAdd = studentService.add(studentTmp);

					if (resultAdd) {
						System.out.println("Them thanh cong!");
					} else {
						System.out.println("Them that bai!");
					}
				}
				break;
			case 3:
				if (classList != null) {
					classList.clear();
				}
				if (studentList != null) {
					studentList.clear();
				}
				classList = classService.getAll();
				if (classList.size() == 0) {
					System.out.println("Khong co lop hoc nao");
				} else {
					studentList = studentService.getAll();
					if (studentList.size() == 0) {
						System.out.println("Khong co hoc sinh nao");
					} else {
						String rollnumber = helper.inputRollnumber(studentList, 1);
						studentUpdateManagement(rollnumber, studentService, classService);
					}
				}
				break;
			case 4:
				if (classList != null) {
					classList.clear();
				}
				if (studentList != null) {
					studentList.clear();
				}
				classList = classService.getAll();
				if (classList.size() == 0) {
					System.out.println("Khong co lop hoc nao");
				} else {
					studentList = studentService.getAll();
					if (studentList.size() == 0) {
						System.out.println("Khong co hoc sinh nao");
					} else {
						String rollnumber = helper.inputRollnumber(studentList, 1);
						studentTmp = studentService.getByRollnumber(rollnumber);
						boolean resultDelete = studentService.delete(studentTmp);

						if (resultDelete) {
							System.out.println("Xoa thanh cong!");
						} else {
							System.out.println("Xoa that bai!");
						}
					}
				}
				break;
			case 5:
				checkOption = false;
				break;
			}

		} while (checkOption);
	}
}
