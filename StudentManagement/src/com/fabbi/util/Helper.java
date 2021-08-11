package com.fabbi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fabbi.entity.Clazz;
import com.fabbi.entity.Student;

public class Helper {

	private static final String ROLLNUMBER_PATTERN = "^(HS)\\d{5}$";
	private static final String DOB_PATTERN = "^\\d{4}[-]\\d{2}[-]\\d{2}$";
	private static Helper instance;
	
	private Helper() {
		
	}
	
	public static Helper getInstance() {
		if (instance == null) {
			instance = new Helper();
		}
		return instance;
	}

	public void mainMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Quan ly lop hoc.");
		System.out.println("2. Quan ly hoc sinh.");
		System.out.println("3. Tim kiem hoc sinh qua ten.");
		System.out.println("4. Tim kiem hoc sinh qua ngay thang nam sinh.");
		System.out.println("5. Hien thi hoc sinh co nam dinh la 2010.");
		System.out.println("6. Sap xep hoc sinh theo ten.");
		System.out.println("7. Sap xep lop hoc theo so luong hoc sinh.");
		System.out.println("8. Thong ke so nam/nu cua lop hoc.");
		System.out.println("9. Thoat.");
		System.out.println("---------------------------------------");
	}

	public void subMenu(String name) {

		System.out.println("---------------");
		System.out.println("1. Hien thi " + name + ".");
		System.out.println("2. Them " + name + ".");
		System.out.println("3. Sua " + name + ".");
		System.out.println("4. Xoa " + name + ".");
		System.out.println("5. Quay lai.");
		System.out.println("---------------");
	}

	public void studentUpdateMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Sua ten.");
		System.out.println("2. Sua ngay thang nam sinh.");
		System.out.println("3. Sua gioi tinh.");
		System.out.println("4. Sua dia chi.");
		System.out.println("5. Sua so thich.");
		System.out.println("6. Chuyen lop hoc.");
		System.out.println("7. Quay lai.");
		System.out.println("---------------------------------------");
	}

	@SuppressWarnings("resource")
	public int inputOption(int min, int max) {

		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.print("Nhap lua chon cua ban: ");
			try {
				option = scanner.nextInt();

				if (option > max || option < min) {
					System.out.printf("Nhap so trong khoang (%d-%d)!", min, max);
					System.out.println();
				} else {

					return option;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Error: " + ex.toString());
				System.out.printf("Nhap so trong khoang (%d-%d)!", min, max);
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}

	@SuppressWarnings("resource")
	public String inputClassName(List<Clazz> classList, int option1, int option2) {

		List<Clazz> matchingClass = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			if (option1 == 0) {
				System.out.print("Nhap ten lop: ");
			} else if (option1 == 1) {
				System.out.print("Nhap ten lop moi: ");
			} else if (option1 == 2) {
				System.out.print("Hoc sinh thuoc lop nao: ");
			}

			String name = scanner.nextLine();
			System.out.print("");
			matchingClass = classList.stream().filter(c -> c.getName().equalsIgnoreCase(name))
					.collect(Collectors.toList());

			if (option2 == 0) {
				if (matchingClass.size() == 0) {

					return name;
				} else {
					System.out.println("Da ton tai!");
				}
			} else if (option2 == 1) {

				if (matchingClass.size() == 0) {
					System.out.println("Khong ton tai!");
				} else {

					return name;
				}
			}

		} while (true);
	}

	@SuppressWarnings("resource")
	public String inputRollnumber(List<Student> studentList, int option) {

		List<Student> matching = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Nhap ma so hoc sinh (dinh dang \"HS*****\" voi \"*\" la so): ");
			String rollnumber = scanner.nextLine();

			if (Pattern.matches(ROLLNUMBER_PATTERN, rollnumber)) {
				matching = studentList.stream().filter(s -> s.getRollnumber().equals(rollnumber))
						.collect(Collectors.toList());
				if (option == 0) {
					if (matching.size() == 0) {

						return rollnumber;
					} else {
						System.out.println("Da ton tai!");
					}
				} else if (option == 1) {
					if (matching.size() == 0) {
						System.out.println("Khong ton tai!");
					} else {
						return rollnumber;
					}
				}

			} else {
				System.out.println("Nhap sai dinh dang!");
			}
		} while (true);
	}

	@SuppressWarnings("resource")
	public String inputGender() {

		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Nhap gioi tinh hoc sinh (nam/nu): ");
			String gender = scanner.nextLine();

			if (gender.equalsIgnoreCase("nam") || gender.equalsIgnoreCase("nu")) {

				return gender;
			} else {
				System.out.println("Nhap sai!");
			}
		} while (true);
	}

	@SuppressWarnings("resource")
	public Date inputDOB() {

		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		do {
			System.out.print("Nhap ngay thang nam sinh (dinh dang \"yyyy-MM-dd\"): ");
			String dob = scanner.nextLine();

			if (Pattern.matches(DOB_PATTERN, dob)) {
				try {
					Date date = dateFormat.parse(dob);

					return date;

				} catch (ParseException e) {
					System.out.println("Nhap sai dinh dang!");
				}

			} else {
				System.out.println("Nhap sai dinh dang!");
			}
		} while (true);
	}
}
