package com.fabbi.business;

import java.util.List;

import com.fabbi.service.ClassService;
import com.fabbi.service.StudentService;
import com.fabbi.util.Helper;
import com.fabbi.entity.Class;
import com.fabbi.entity.Student;

public class ClassManagement {

	private Helper helper = null;
	
	public void statisticByGender(StudentService studentService) {
		List<Student> studentListMale = studentService.getStudentListByGender("nam");
		List<Student> studentListFemale = studentService.getStudentListByGender("nu");
	}

	public void sortByStudentQuantity(ClassService classService) {
		List<Class> classList = classService.sortByTotalStudent();

		if (classList.size() == 0) {
			System.out.println("Khong co lop hoc nao!");
		} else {
			for (Class classTmp : classList) {
				System.out.printf("Class ID: %d - Class Name: %s - Total Student: %d", classTmp.getId(),
						classTmp.getName(), classTmp.getTotalStudent());
				System.out.println();
			}
		}
	}

	public void classManagementExecute(ClassService classService, StudentService studentService) {

		if (helper == null) {
			helper = new Helper();
		}

		int option = 0;
		boolean checkOption = true;
		List<Class> classList = null;
		Class classTmp = null;

		do {
			System.out.println();
			System.out.println(">>>>>> Quan ly lop hoc <<<<<<");
			helper.subMenu("lop hoc");
			option = helper.inputOption(1, 5);

			switch (option) {
			case 1:
				classList = classService.getAll();

				if (classList.size() == 0) {
					System.out.println("Khong co lop hoc nao!");
				} else {
					classList.forEach(System.out::println);
				}
				break;
			case 2:
				classList = classService.getAll();
				String className = helper.inputClassName(classList, 0, 0);
				boolean resultAdd = classService.add(className);

				if (resultAdd) {
					System.out.println("Them thanh cong!");
				} else {
					System.out.println("Them that bai!");
				}
				break;
			case 3:
				classList = classService.getAll();

				if (classList.size() == 0) {
					System.out.println("Khong co lop nao.");
				} else {
					String classNameOld = helper.inputClassName(classList, 0, 1);
					String classNameNew = helper.inputClassName(classList, 1, 0);
					classTmp = classService.getByName(classNameOld);
					classTmp.setName(classNameNew);
					boolean resultUpdate = classService.update(classTmp);

					if (resultUpdate) {
						System.out.println("Sua thanh cong!");
					} else {
						System.out.println("Sua that bai!");
					}
				}
				break;
			case 4:
				classList = classService.getAll();

				if (classList.size() == 0) {
					System.out.println("Khong co lop nao.");
				} else {
					String classNameDelete = helper.inputClassName(classList, 0, 1);
					classTmp = classService.getByName(classNameDelete);
					int size = studentService.getByClassId(classTmp.getId()).size();

					if (size == 0) {
						boolean resultDelete = classService.delete(classNameDelete);

						if (resultDelete) {
							System.out.println("Xoa thanh cong!");
						} else {
							System.out.println("Xoa that bai!");
						}
					} else {
						System.out.printf("Xoa that bai! Co hoc sinh thuoc lop \"%s\".", classNameDelete);
						System.out.println();
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
