package practice7;

import org.junit.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.LinkedList;


public class MySQLHighSchoolTest {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    @Test
    public void executePractice5Query() throws Exception {
        MySQLHighSchool test = new MySQLHighSchool();
        List<Schedule> listOfSchedules = test.readTeacherSchedule();
        Assert.assertNotNull("method returned null list of schedules", listOfSchedules);

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager
            .getConnection("jdbc:mysql://localhost/highschool?" + 
                "user=root&password=mysqlpass123");
        statement = connection.createStatement();
        /*
            The following query gets the CourseIDs of the courses assigned to
            teacher Edsger Dijkstra, whose TeacherID is 1.
        */
        resultSet = statement.executeQuery("select courseID from COURSE where COURSEAssignedTeacher=1");
        boolean flag = true;
        int i = 0;
        while(resultSet.next() && flag) {
            flag = flag || resultSet.getInt("courseID") == listOfSchedules.get(i).getCourseID();
            i++;
        }
        
        Assert.assertTrue("\n\nFailed checking output\n\n", flag);
    }
}
