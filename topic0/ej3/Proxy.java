

public class Proxy {

    DBConnection realConnection = null;
    
    
    public void access() {
        /*
            HERE you might write some code to control the access rights of the 
            class that's calling this method.
        */
        
        if (realConnection == null) {
            realConnection = new DBConnection();
            System.out.println("Connection created using Proxy");
        }
        realConnection.access();
    }
    
    public void executeQuery(String query) {
        /*
            HERE you might write some code to control the access rights of the 
            class that's trying to execute the Query.
        */
        if (realConnection == null) {
            realConnection = new DBConnection();
            System.out.println("Connection created using Proxy");
        }
        
        System.out.println("Executing query trough proxy");
        realConnection.executeQuery(query);
    }
}
