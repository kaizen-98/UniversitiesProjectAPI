package UniversitiesProjectMainPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class TestClass {
	public static void main(String[] args) {
		String url1 = "jdbc:sqlserver://localhost:1433;" + "databaseName=javaapi;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter country name to see university...Please Start with capital letter");
		String country = scanner.next();
		String apiUrl = "http://universities.hipolabs.com/search?country=" + country;
		Connection conn1 = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			conn1 = DriverManager.getConnection(url1, user, pass);
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			StringBuilder json = new StringBuilder();
			while ((output = br.readLine()) != null) {
				json.append(output);
			}
			conn.disconnect();
			Gson gson = new Gson();
			MyObject[] universities = gson.fromJson(json.toString(), MyObject[].class);
			String insertSql = "INSERT INTO universities (name, domain, website, country, alpha_code) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn1.prepareStatement(insertSql);
			for (MyObject university : universities) {
				System.out.println("Name: " + university.getName());
				System.out.println("Domain: " + university.getDomains());
				System.out.println("Website: " + university.getWebPages());
				System.out.println("Country: " + university.getCountry());
				System.out.println("Alpha Code: " + university.getAlphaTwoCode());
				System.out.println();
				statement.setString(1, university.getName());
				statement.setString(2, university.getDomains().get(0));
				statement.setString(3, university.getWebPages().get(0));
				statement.setString(4, university.getCountry());
				statement.setString(5, university.getAlphaTwoCode());
				statement.addBatch();
			}
			statement.executeBatch();
			statement.close();
			conn1.close();// database
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
