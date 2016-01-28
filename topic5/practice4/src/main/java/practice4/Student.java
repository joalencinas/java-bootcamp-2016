package practice4;

import org.mongodb.morphia.annotations.*;
import org.bson.types.ObjectId;

@Entity
public class Student implements Comparable<Student> {
    
    @Id private ObjectId id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    
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
    
    public int compareTo(Student student) {
        int result = this.lastName.compareTo(student.lastName);
        if (result == 0) {
            result = (this.lastName.compareTo(student.firstName));
        }
        return result;
    }
}
