package practice2-4;

public class Student {
    
    @Id private int id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    
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
}
