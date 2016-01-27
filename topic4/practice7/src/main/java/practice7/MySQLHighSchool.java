package practice7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLHighSchool {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public void readTeacherSchedule() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost/highschool?" + 
                    "user=root&password=mysqlpass123");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select  TEACHERLastName," + 
                "TEACHERFirstName, day, starts, ends, COURSEName " +
                "from TEACHER join COURSE on COURSEAssignedTeacher=teacherID " +
                "join SCHEDULE on SCHEDULECourseID=courseID where teacherID=1;");
            boolean first = true;
            while (resultSet.next()) {
                if (first) {
                    first = false;
                    System.out.printf("\nTeacher: %s, %s\nSchedule: \n", resultSet.getString(1), 
                        resultSet.getString(2));
                }
                String day = resultSet.getString("day");
                String starts = resultSet.getString("starts");
                String ends = resultSet.getString("ends");
                String courseName = resultSet.getString("COURSEName");
                System.out.printf("  %s %s - %s: %s\n", day, starts, ends, courseName);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

  // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }

}
