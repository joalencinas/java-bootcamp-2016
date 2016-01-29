package practice7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.LinkedList;

public class MySQLHighSchool {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private List<Schedule> resultSchedule = null;

    /**
     *Reads Schedule for teacher whose Id is 1, returns FALSE if the result
     *set returned by executeQuery is empty.
     */

    public List<Schedule> readTeacherSchedule() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost/highschool?" + 
                    "user=root&password=mysqlpass123");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select  TEACHERLastName," + 
                "TEACHERFirstName, SCHEDULECourseID, day, starts, ends, COURSEName " +
                "from TEACHER join COURSE on COURSEAssignedTeacher=teacherID " +
                "join SCHEDULE on SCHEDULECourseID=courseID where teacherID=1;");
            boolean first = false;
            resultSchedule = new LinkedList();
            Schedule schedule = null;
            while (resultSet.next()) {
                if (!first) {
                    first = true;
                    System.out.printf("\nTeacher: %s, %s\nSchedule: \n", resultSet.getString(1), 
                        resultSet.getString(2));
                }
                schedule = new Schedule();
                int courseID = resultSet.getInt("SCHEDULECourseID");
                schedule.setCourseID(courseID);
                String day = resultSet.getString("day");
                schedule.setDay(day);
                String starts = resultSet.getString("starts");
                schedule.setStart(starts);
                String ends = resultSet.getString("ends");
                schedule.setEnd(ends);
                String courseName = resultSet.getString("COURSEName");
                System.out.printf("  %s %s - %s: %s\n", day, starts, ends, courseName);
                resultSchedule.add(schedule);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
            return resultSchedule;
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
