package topic7;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;

@Api
public class User implements Comparable<User> {
    
    @Id
    private final String username;
    
    @JsonIgnore
    private final String password;
    
    private String firstName;
    private String lastName;
    private boolean online;
    
    private List<Product> shoppingCart;
    
    
    
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shoppingCart = new LinkedList();
        this.online = false;
    }
    
    public boolean login(String password) {
        online = (this.password.compareTo(password) == 0);
        return online;
    }
    
    public void logout() {
        online = false;
    }
    
    public boolean isLoggedIn() {
        return online;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns the username", required = true)
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        if (online) {
            return password;
        } else {
            System.out.printf("\n\n\nUSER NOT LOGGED IN, CAN'T RETURN PASSWORD\n\n\n");
            return null;
        }
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
    
    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public List<Product> getShoppingCart() {
        return shoppingCart;
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
              this.password.compareTo(anotherUser.password) == 0 &&
                this.firstName.compareTo(anotherUser.firstName) == 0 &&
                  this.lastName.compareTo(anotherUser.lastName) == 0 &&
                    this.shoppingCart.equals(anotherUser.shoppingCart)) {
            result = 0;
        } else {
            result = this.username.compareTo(anotherUser.username);
        }
        return result;
    }
    
}


