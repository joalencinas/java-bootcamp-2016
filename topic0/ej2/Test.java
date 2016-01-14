
public class Test {

    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstractFactory();

        ConnectionFactory connectionFactory1 = abstractFactory.getConnectionFactory("sql");
        DBConnection conn1 = connectionFactory1.getConnection("SQLtype1");
        System.out.println("conn1 is " + conn1.makeConnection());
        DBConnection conn2 = connectionFactory1.getConnection("SQLtype2");
        System.out.println("conn2 is " + conn2.makeConnection());

        ConnectionFactory connectionFactory2 = abstractFactory.getConnectionFactory("NoSql");
        DBConnection conn3 = connectionFactory2.getConnection("NoSQLType1");
        System.out.println("conn3 is " + conn3.makeConnection());
        DBConnection conn4 = connectionFactory2.getConnection("NoSQLType2");
        System.out.println("conn4 is " + conn4.makeConnection());

    }

}

