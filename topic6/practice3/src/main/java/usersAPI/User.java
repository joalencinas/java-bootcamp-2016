package usersAPI;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;

@Api
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
    
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns the username", required = true)
    public String getUsername() {
        return username;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Sets user's firstName", required = true)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns first name", required = true)
    public String getFirstName() {
        return firstName;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Sets lastName", required = true)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns user's lastName", required = true)
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public String toString() {
        return String.format(
            "User data: username = %s, First Name = %s, Last Name = %s   ",
            username, firstName, lastName);
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Two users are equal if and only if every one of their fields are equal", required = true)
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


