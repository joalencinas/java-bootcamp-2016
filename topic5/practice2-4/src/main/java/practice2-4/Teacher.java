package practice2-4;

import java.util.*;
import org.mongodb.morphia.annotations.*;


@Entity
public class Teacher {
    
    @Id private int id;
    private String firstName;
    private String lastName;
    private String[] assignedCourses;
    
    public int getId() {
        return id;
    }
    
    public void setFirstName(String firstname) {
        firstName = firstname;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastname) {
        lastname = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setAssignedCourses(String[] courses) {
//        if (verifyCourses(courses))
            assignedCourses = courses;
    
    }
    
    public String[] getAssignedCourses() {
        return Array.copyOf(assignedCourses, assignedCourses.length);
    }
    
/*    private boolean verifyCourses(String[] courses) {
        boolean result = true;
        Array.sort(courses);
        for (int i = 0; i<courses.length - 1; i++) {
            if(courses[i] < 1 || courses[i] = courses[i+1]) {
                result = false;
                break;
            }
        }
        return result;
    }
*/

}
