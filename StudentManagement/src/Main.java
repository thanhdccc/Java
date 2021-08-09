import com.fabbi.business.ClassManagement;
import com.fabbi.business.StudentManagement;
import com.fabbi.service.ClassService;
import com.fabbi.service.StudentService;
import com.fabbi.util.Helper;

public class Main {

	private static Helper helper = null;
	private static ClassService classService = null;
	private static ClassManagement classHelper = null;
	private static StudentService studentService = null;
	private static StudentManagement studentHelper = null;

	public static void main(String[] args) {

		if (helper == null) {
			helper = new Helper();
		}
		if (classHelper == null) {
			classHelper = new ClassManagement();
		}
		if (studentHelper == null) {
			studentHelper = new StudentManagement();
		}
		if (classService == null) {
			classService = new ClassService();
		}
		if (studentService == null) {
			studentService = new StudentService();
		}
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
				break;
			case 9:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}

}
