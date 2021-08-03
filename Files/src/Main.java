import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static final String MAIN_FOLDER_NAME = "documents";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter folder path: ");
		String folderPath = scanner.nextLine();
		File mainFolder = new File(folderPath + MAIN_FOLDER_NAME);
		
		if (!mainFolder.exists()) {
			if (mainFolder.mkdir()) {
				String tmp = mainFolder.getAbsolutePath() + "\\";
				ExecutorService pool = Executors.newFixedThreadPool(10);
				for (int i = 1; i <= 10; i++) {
					pool.execute(new Thread(new CreateSubFolder(tmp, i)));
				}
				pool.shutdown();

				System.out.println("Done!");
			} else {
				System.out.println("Create Failed!");
			}
		} else {
			System.out.println("Folder already exists!");
		}
	}
}
