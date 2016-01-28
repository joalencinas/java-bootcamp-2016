package practice4;

import org.mongodb.morphia.annotations.*;
import org.bson.types.ObjectId;

@Entity
public class Note {
    
    @Id private ObjectId id;
    @Reference
    private Course course;
    @Reference
    private Student student;
    private int firstNote;
    private int secondNote;
    private int thirdNote;
    private double finalNote;
    
    public ObjectId getId() {
        return id;
    }
    
    public void setCourse(Course newCourse) {
        course = newCourse;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setStudent(Student newStudent) {
        student = newStudent;
    }
    
    public Student getStudent() {
        return student;
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
