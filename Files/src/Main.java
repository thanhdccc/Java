import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
				pool.execute(new Thread(new CreateSubFolder(tmp, 1)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 2)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 3)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 4)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 5)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 6)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 7)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 8)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 9)));
				pool.execute(new Thread(new CreateSubFolder(tmp, 10)));
				pool.shutdown();
				try {
					if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
						pool.shutdownNow();
					}
				} catch (InterruptedException ex) {
					pool.shutdownNow();
					Thread.currentThread().interrupt();
				}
				System.out.println("Done!");
			} else {
				System.out.println("Create Failed!");
			}
		} else {
			System.out.println("Folder already exists!");
		}
	}
}
