

public class NoSqlFactory extends ConnectionFactory {
    
    
    @Override
    public DBConnection getConnection(String type) {
        DBConnection result = null;
        switch (type)
        {
            case "NoSQLType1":
                result = new NoSqlConnection1();
                break;
            default:
                result = new NoSqlConnection2();
                break;
        }
        return result;
    }
}
