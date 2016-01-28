package practice4;

import java.util.*;
import org.mongodb.morphia.annotations.*;
import org.bson.types.ObjectId;

@Entity
public class Course implements Comparable<Course> {
    
    @Id ObjectId id;
    private String courseName;
    private int hoursPerWeek;
    private List<String> schedule;
    
    public ObjectId getId() {
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
    
    public void setSchedule(List<String> newSchedule) {
        schedule = newSchedule;
    }
    
    public List<String> getSchedule() {
        return schedule;
    }
    
    public int compareTo(Course anotherCourse) {
        return courseName.compareTo(anotherCourse.getCourseName());
    }



}
