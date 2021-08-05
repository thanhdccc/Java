import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CrawlDataService {

	private static final String WEB_URL = "https://vnexpress.net/ba-giai-phap-go-un-tac-hang-hoa-tai-cang-cat-lai-4334237.html";
	private static final String FILE_EXTENSION = ".jpg";
	private static final String FILE_DATA = "data.html";
	
	public List<String> getAllImgUrl(String fileName) {
		try {
			File input = new File(fileName);
			
			Document doc = Jsoup.parse(input, "UTF-8");
			List<String> imgUrlList = new ArrayList<>();
			
			Elements element1 = doc.getElementsByTag("meta");
			
			for (int i = 0; i < element1.size(); i++) {
				String url = element1.get(i).absUrl("content");
				if (url.equals("")) {
					continue;
				} else {
					imgUrlList.add(url);
				}
			}
			
			return imgUrlList;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveImage(String imageUrl, String destinationFile) throws IOException {
	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream(destinationFile);

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();
	}
	
	public void execute() {
		URL urlObj;
		try {
			urlObj = new URL(WEB_URL);
			URLConnection urlCon = urlObj.openConnection();
			 
			InputStream inputStream = urlCon.getInputStream();
			BufferedInputStream reader = new BufferedInputStream(inputStream);
			 
			BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(FILE_DATA));
			 
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = reader.read(buffer)) != -1) {
			    writer.write(buffer, 0, bytesRead);
			}
			
			writer.close();
			reader.close();
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> imgListTmp = getAllImgUrl(FILE_DATA);
		List<String> imgList = new ArrayList<>();
		for (int i = 0; i < imgListTmp.size(); i++) {
			if (imgListTmp.get(i).contains(FILE_EXTENSION)) {
				imgList.add(imgListTmp.get(i));
			}
		}
		
		for (int i = 0; i < imgList.size(); i++) {
			String tmp = i + FILE_EXTENSION;
			try {
				saveImage(imgList.get(i), tmp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Done");
	}
}
