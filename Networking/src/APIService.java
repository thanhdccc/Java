import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class APIService {

	private static HttpURLConnection connection;
	private static final String ACCESS_TOKEN = "c95459da3486560532f841cc0388959d776573616d04b52409f76fc87cf61e68";
	
	public void create() {
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
	
	public void getById(int id) {
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
	
	public void updateById(int id) {
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
	
	public void deleteById(int id) {
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
}
