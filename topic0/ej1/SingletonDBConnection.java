//package topic0.ej1;

//import java.sql.DriverManager;



public class SingletonDBConnection {

	private static SingletonDBConnection singletonCon = null;
	
	
	private SingletonDBConnection() {
/*      
        HERE goes the actual connection code
		
		String driver = "";
		String url = "";
		String username = "myName";
		String password = "myPass";	
		try {
			Class.forName(driver);
			DriverManager.getConnection(url,username,password);
			System.out.println("Connection created\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}
	
	public static SingletonDBConnection getInstance() {
		if (singletonCon == null) {
			singletonCon = new SingletonDBConnection();
		}
		return singletonCon;
		
	}
	
    public static String getConnection() {
        return ("\n Connected.. \n");
        
    
    }
    
    public static void main (String[] args)
    {
        SingletonDBConnection singleconn = SingletonDBConnection.getInstance();
        System.out.println(singleconn.getConnection());
        return;
    }


}

