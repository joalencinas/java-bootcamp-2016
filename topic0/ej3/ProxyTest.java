
import java.util.Scanner;


public class ProxyTest {
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        Proxy testProxy = new Proxy();
        testProxy.access();
        System.out.println("Enter your query: ");
        
        String query = stdin.next();
        testProxy.executeQuery(query);
    }
}

