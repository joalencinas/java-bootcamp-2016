package practice7;

import org.junit.*;

public class MySQLHighSchoolTest {
    @Test
    public void executePractice5Query() throws Exception {
        MySQLHighSchool test = new MySQLHighSchool();
        test.readTeacherSchedule();
        Assert.assertTrue(true);
    }
}
