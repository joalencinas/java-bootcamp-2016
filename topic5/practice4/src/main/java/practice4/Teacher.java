package practice4;

import java.util.*;
import org.mongodb.morphia.annotations.*;
import org.bson.types.ObjectId;

@Entity
public class Teacher {
    
    @Id private ObjectId id;
    private String firstName;
    private String lastName;
    @Reference
    private List<Course> assignedCourses;
    
    public ObjectId getId() {
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
    
    public void setAssignedCourses(List<Course> courses) {
//        if (verifyCourses(courses))
            assignedCourses = courses;
    
    }
    
    public List<Course> getAssignedCourses() {
        return assignedCourses;
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
