

public class DBConnection {
    
    public void access(){
        /*
            HERE goes the code to actually make the connection
        */
        System.out.println("Access granted to Database\n");
    }
    public void executeQuery(String query) {
        /*
            HERE goes the code to execute the query
        */
        System.out.println("Your database query has been processed");
        System.out.println("Your query was: " + query);
    }
}
