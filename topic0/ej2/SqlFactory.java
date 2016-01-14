

public class SqlFactory extends ConnectionFactory {
    
    
    @Override
    public DBConnection getConnection(String type) {
        DBConnection result = null;
        switch (type)
        {
            case "SQLtype1":
                result = new SqlConnection1();
                break;
            default:
                result = new SqlConnection2();
                break;
        }
        return result;
    }
}
