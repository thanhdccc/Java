import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateSubFolder implements Runnable {

	private static final String SUB_FOLDER_NAME = "doc";
	private static final String FILE_NAME = "content.txt";
	private String path;
	private int number;

	public CreateSubFolder(String path, int number) {
		this.path = path;
		this.number = number;
	}

	@Override
	public void run() {
		String folderTmp = path + SUB_FOLDER_NAME + number;
		File subFolder = new File(folderTmp);
		
		if (!subFolder.exists()) {
			if (subFolder.mkdir()) {
				String subPath = subFolder.getAbsolutePath() + "\\";
				File contentFile = new File(subPath + FILE_NAME);
				
				try {
					if (contentFile.createNewFile()) {
						String pathTmp = contentFile.getAbsolutePath();
						String pathFile = new File(pathTmp).getParent();
						FileWriter writer = new FileWriter(contentFile, false);
						writer.write(pathFile);
						writer.close();
					}
				} catch (IOException e) {
					System.out.println("Error: " + e.toString());
				}
			}
		}
	}

}
