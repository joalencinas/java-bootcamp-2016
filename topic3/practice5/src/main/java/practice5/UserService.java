package practice5;

/**
 * User service for CRUD operations
 *
 */
public interface UserService {
    /**
     * Adds new user to database
     * @param user User to be added.
     */
    public void add(User user);
    
    /**
     * Reads user information.
     * @param id ID of the User that will be read.
     */
    public User read(int id);
    
    /**
     * Updates data about the given User
     * @param user User to be updated on database.
     */
    public void update(User user);
    
    /**
     * Deletes the specified User
     * @param user User to be deleted from database
     */
    public void delete(User user);
}
