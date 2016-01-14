package topic0.ej1;

import java.sql.Connection;
import java.sql.DriverManager;



public class SingletonDBConnection {

	private static SingletonDBConnection singletonCon = null;
	private static Connection conn;
	
	
	private SingletonDBConnection() {
		
		String driver = "";
		String url = "";
		String username = "myName";
		String password = "myPass";	
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection created\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SingletonDBConnection getSingletonCon() {
		if (singletonCon == null) {
			singletonCon = new SingletonDBConnection();
		}
		return singletonCon;
		
	}
	
	public static Connection getConnection() {
		return conn;
	}


}
