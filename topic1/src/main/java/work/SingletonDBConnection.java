package work;

/**
 * Hello world!
 *
 */
public class SingletonDBConnection {

    private static SingletonDBConnection singletonCon = null;
    
    
    private SingletonDBConnection() {
/*
        HERE goes the actual connection code
*/
    System.out.println("Connection was successfully done");

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

