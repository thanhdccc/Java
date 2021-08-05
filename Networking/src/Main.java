import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

	private static HttpURLConnection connection;
	private static final String ACCESS_TOKEN = "c95459da3486560532f841cc0388959d776573616d04b52409f76fc87cf61e68";
	private static final String WEB_URL = "https://vnexpress.net/ba-giai-phap-go-un-tac-hang-hoa-tai-cang-cat-lai-4334237.html";
	
	private static void create() {
		try {
			URL url = new URL("https://gorest.co.in/public/v1/users");
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization","Bearer "+ACCESS_TOKEN);
			connection.setRequestMethod("POST");
			
			String jsonInputString = "{\"name\": \"Nguyen Thi A\", \"gender\": \"female\", \"email\": \"nta@mail.com\", \"status\": \"active\"}";
			
			OutputStream os = connection.getOutputStream();
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			
			os.close();
			int status = connection.getResponseCode();
			String message = connection.getResponseMessage();
			System.out.println(status);
			System.out.println(message);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
			
			br.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getById(int id) {
		try {
			URL url = new URL("https://gorest.co.in/public/v1/users/" + id);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestMethod("GET");

			int status = connection.getResponseCode();
			String message = connection.getResponseMessage();
			System.out.println(status);
			System.out.println(message);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
			
			br.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateById(int id) {
		try {
			URL url = new URL("https://gorest.co.in/public/v1/users/" + id);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization","Bearer " + ACCESS_TOKEN);
			connection.setRequestMethod("PUT");
			
			String jsonInputString = "{\"name\": \"Nguyen Thi A Update\", \"gender\": \"female\", \"email\": \"nta@mail.com\", \"status\": \"active\"}";
			
			OutputStream os = connection.getOutputStream();
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			
			os.close();

			int status = connection.getResponseCode();
			String message = connection.getResponseMessage();
			System.out.println(status);
			System.out.println(message);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteById(int id) {
		try {
			URL url = new URL("https://gorest.co.in/public/v1/users/" + id);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization","Bearer " + ACCESS_TOKEN);
			connection.setRequestMethod("DELETE");

			int status = connection.getResponseCode();
			String message = connection.getResponseMessage();
			System.out.println(status);
			System.out.println(message);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void mainMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Call API.");
		System.out.println("2. Download Image.");
		System.out.println("3. Exit.");
		System.out.println("---------------------------------------");
	}
	
	private static void subMenu() {

		System.out.println("---------------------------------------");
		System.out.println("1. Create (POST).");
		System.out.println("2. List (GET).");
		System.out.println("3. Update (PUT).");
		System.out.println("4. Delete (DELETE).");
		System.out.println("5. Back.");
		System.out.println("---------------------------------------");
	}
	
	@SuppressWarnings("resource")
	private static int inputOption(int min, int max) {

		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.print("Enter your choise: ");
			try {
				option = scanner.nextInt();

				if (option > max || option < min) {
					System.out.printf("Please input number (%d-%d)!", min, max);
					System.out.println();
				} else {

					return option;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Message: " + ex.toString());
				System.out.printf("Please input number (%d-%d)!", min, max);
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}
	
	@SuppressWarnings("resource")
	private static int inputInteger() {
		
		Scanner scanner = new Scanner(System.in);
		int id;

		do {
			System.out.print("Enter ID: ");
			try {
				id = scanner.nextInt();
				
				return id;
			} catch (InputMismatchException ex) {
				System.out.println("Message: " + ex.toString());
				System.out.println();
			}
			scanner.nextLine();
		} while (true);
	}
	
	private static List<String> getAllImgUrl(String fileName) {
		try {
			File input = new File(fileName);
			
			Document doc = Jsoup.parse(input, "UTF-8");
			List<String> imgUrlList = new ArrayList<>();
//			Elements element = doc.getElementsByTag("img");
//			
//			for (int i = 0; i < element.size(); i++) {
//				String url = element.get(i).absUrl("src");
//				if (url.equals("")) {
//					continue;
//				} else {
//					imgUrlList.add(url);
//				}
//			}
			
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

	private static void saveImage(String imageUrl, String destinationFile) throws IOException {
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
	
	public static void main(String[] args) {

		int option = 0;
		boolean checkOption = true;

		do {
			System.out.println();
			System.out.println(">>>>>>------ Main Menu ------<<<<<<");
			mainMenu();
			option = inputOption(1, 3);

			switch (option) {
			case 1:
				int optionCase1 = 0;
				boolean checkOptionCase1 = true;
				
				do {
					System.out.println();
					System.out.println(">>>>>>------ Call API ------<<<<<<");
					subMenu();
					optionCase1 = inputOption(1, 5);

					switch (optionCase1) {
					case 1:
						create();
						break;
					case 2:
						int idGet = inputInteger();
						getById(idGet);
						break;
					case 3:
						int idUpdate = inputInteger();
						updateById(idUpdate);
						break;
					case 4:
						int idDelete = inputInteger();
						deleteById(idDelete);
						break;
					case 5:
						checkOptionCase1 = false;
						break;
					}
				} while (checkOptionCase1);
				break;
			case 2:
				String filePath = "data.html";
				 
				URL urlObj;
				try {
					urlObj = new URL(WEB_URL);
					URLConnection urlCon = urlObj.openConnection();
					 
					InputStream inputStream = urlCon.getInputStream();
					BufferedInputStream reader = new BufferedInputStream(inputStream);
					 
					BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));
					 
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
				
				List<String> imgListTmp = getAllImgUrl(filePath);
				List<String> imgList = new ArrayList<>();
				for (int i = 0; i < imgListTmp.size(); i++) {
					if (imgListTmp.get(i).contains(".jpg")) {
						imgList.add(imgListTmp.get(i));
					}
				}
				
				for (int i = 0; i < imgList.size(); i++) {
					String tmp = i + ".jpg";
					try {
						saveImage(imgList.get(i), tmp);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("Done");
				break;
			case 3:
				checkOption = false;
				break;
			}
		} while (checkOption);
	}
}
