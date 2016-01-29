package practice7;

/**
 * Schedule POJO, used to test the MySQLHighSchool query made by its
 * readTeacherSchedule method. Notice that starts and ends fields are declared 
 * as Strings, this is meant to simplify the POJO.
 */

public class Schedule {
    
    private int courseID;
    private String day;
    private String start;
    private String end;
    
    public void setCourseID(int courseid) {
        courseID = courseid;
    }
    
    public int getCourseID() {
        return courseID;
    }
    
    public void setDay(String day) {
        this.day = day;
    }
    
    public String getDay() {
        return day;
    }
    
    public void setStart(String start) {
        this.start = start;
    }
    
    public String getStart() {
        return start;
    }
    
    public void setEnd(String end) {
        this.end = end;
    }
    
    public String getEnd() {
        return end;
    }
    
}
