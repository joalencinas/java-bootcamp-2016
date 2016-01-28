package practice2-4;

import org.mongodb.morphia.annotations.*;

@Entity
public class Note {
    
    @Id private int id;
    private String firstName;
    private String lastName;
    private int courseId;
    private int firstNote;
    private int secondNote;
    private int thirdNote;
    private double finalNote;
    
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
    
    public void setCourseId(int courseid) {
        courseId = courseid;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setFirstNote(int firstnote) {
        firstNote = firstnote;
    }
    
    public int getFirstNote() {
        return firstNote;
    }
    
    public void setSecondNote(int secondnote) {
        secondNote = secondnote;
    }
    
    public int getSecondNote() {
        return secondNote;
    }
    
    public void setThirdNote(int thirdnote) {
        thirdNote = thirdnote;
    }
    
    public int getThirdNote() {
        return thirdNote;
    }
    
    public void setFinalNote(double finalnote) {
        finalNote = finalnote;
    }
    
    public double getFinalNote() {
        return finalNote;
    }

}
