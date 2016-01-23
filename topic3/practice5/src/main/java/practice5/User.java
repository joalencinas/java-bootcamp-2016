package practice5;

public class User {
    
    private int id;
    private String name;
    private String email;
    
    public User() {
        id = 0;
    }
    
    public User(int id) {
        this.id = id;
    }
    public User(int id, String name, String email) {
        
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
}
