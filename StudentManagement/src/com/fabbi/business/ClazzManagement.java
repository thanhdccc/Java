package com.fabbi.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fabbi.service.ClazzServiceImpl;
import com.fabbi.service.StudentServiceImpl;
import com.fabbi.util.Helper;
import com.fabbi.entity.Clazz;
import com.fabbi.entity.ClazzDetail;
import com.fabbi.entity.Student;

public class ClazzManagement {

	private Helper helper = null;
	private static ClazzManagement instance;
	
	private ClazzManagement() {
		
	}
	
	public static ClazzManagement getInstance() {
		if (instance == null) {
			instance = new ClazzManagement();
		}
		return instance;
	}

	public void statisticByGender(StudentServiceImpl studentService, ClazzServiceImpl classService) {
		List<Clazz> classList = classService.getAll();
		List<Student> studentListMale = studentService.getStudentListByGender("nam");
		List<Student> studentListFemale = studentService.getStudentListByGender("nu");

		List<ClazzDetail> statisticList = new ArrayList<>();
		List<Student> studentListMaleByClassId = null;
		List<Student> studentListFemaleByClassId = null;

		for (Clazz classObj : classList) {
			studentListMaleByClassId = new ArrayList<>();
			studentListFemaleByClassId = new ArrayList<>();
			studentListMaleByClassId = studentListMale.stream().filter(s -> s.getClassId() == classObj.getId())
					.collect(Collectors.toList());

			studentListFemaleByClassId = studentListFemale.stream().filter(s -> s.getClassId() == classObj.getId())
					.collect(Collectors.toList());

			int totalMale = studentListMaleByClassId.size();
			int totalFemale = studentListFemaleByClassId.size();
			ClazzDetail classTmp = new ClazzDetail(classObj.getId(), classObj.getName(), totalMale, totalFemale);
			statisticList.add(classTmp);
		}

		for (ClazzDetail classObj : statisticList) {
			System.out.printf("ID lop: %d - Ten lop: %s - Hoc sinh nam: %d - Hoc sinh nu: %d", classObj.getId(),
					classObj.getName(), classObj.getTotalMale(), classObj.getTotalFemale());
			System.out.println();
		}
	}

	public void sortByStudentQuantity(ClazzServiceImpl classService) {
		List<ClazzDetail> classList = classService.sortByTotalStudent();

		if (classList.size() == 0) {
			System.out.println("Khong co lop hoc nao!");
		} else {
			for (ClazzDetail classTmp : classList) {
				System.out.printf("ID lop: %d - Ten lop: %s - Tong so hoc sinh: %d", classTmp.getId(),
						classTmp.getName(), classTmp.getTotalStudent());
				System.out.println();
			}
		}
	}

	public void classManagementExecute(ClazzServiceImpl classService, StudentServiceImpl studentService) {

		helper = Helper.getInstance();
		int option = 0;
		boolean checkOption = true;
		List<Clazz> classList = null;
		Clazz classTmp = null;

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
				classTmp = new Clazz(className);
				boolean resultAdd = classService.add(classTmp);

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
						boolean resultDelete = classService.delete(classTmp);

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
