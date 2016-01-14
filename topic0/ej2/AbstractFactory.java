

public class AbstractFactory
{
    public ConnectionFactory getConnectionFactory(String type)
    {
        ConnectionFactory factory = null;
        switch (type)
        {
            case "sql":
                factory = new SqlFactory();
                break;
            default:
                factory = new NoSqlFactory();
                break;
        }
        return factory;
    }
}

