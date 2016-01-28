package practice7;

import org.junit.*;

public class MySQLHighSchoolTest {
    @Test
    public void executePractice5Query() throws Exception {
        MySQLHighSchool test = new MySQLHighSchool();
        boolean flag = test.readTeacherSchedule();
        Assert.assertTrue("Resulting list is EMPTY",flag);
    }
}
