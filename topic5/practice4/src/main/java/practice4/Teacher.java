package practice4;

import java.util.*;
import org.mongodb.morphia.annotations.*;
import org.bson.types.ObjectId;

@Entity
public class Teacher {
    
    @Id private ObjectId id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
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
        lastName = lastname;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setDateOfBirth(String dateofbirth) {
        dateOfBirth = dateofbirth;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setAssignedCourses(List<Course> courses) {
        assignedCourses = courses;
    
    }
    
    public List<Course> getAssignedCourses() {
        return assignedCourses;
    }
    
    
}
