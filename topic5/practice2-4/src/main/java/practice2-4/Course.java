package practice2-4;

import java.util.*;
import org.mongodb.morphia.annotations.*;

@Entity
public class Course {
    
    @Id private int id;
    private String courseName;
    private int hoursPerWeek;
    private String[] schedule;
    
    public int getId() {
        return id;
    }
    
    public void setCourseName(String coursename) {
        courseName = coursename;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setHoursPerWeek(int hoursperweek) {
        hoursPerWeek = hoursperweek;
    }
    
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }
    
    public void setSchedule (String[] newSchedule) {
        schedule = newSchedule;
    }
    
    public String getSchedule () {
        return Array.copyOf(schedule, schedule.length);
    }



}
