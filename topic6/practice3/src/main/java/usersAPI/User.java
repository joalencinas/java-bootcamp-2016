package usersAPI;

import org.springframework.data.annotation.Id;

public class User implements Comparable<User> {
    
    @Id
    private final String username;
    
    private String firstName;
    private String lastName;
    
    
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String toString() {
        return String.format(
            "User data: username=%s, First Name=%s, Last Name=%s",
            username, firstName, lastName);
    }
    
    @Override
    public int compareTo(User anotherUser) {
        int result = 1;
        if (this.username.compareTo(anotherUser.username) == 0 && 
                this.firstName.compareTo(anotherUser.firstName) == 0 &&
                    this.lastName.compareTo(anotherUser.lastName) == 0) {
            result = 0;
        }
        return result;
    }
    
}


