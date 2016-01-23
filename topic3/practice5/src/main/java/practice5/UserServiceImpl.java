package practice5;

import java.util.*;
/**
 * UserService implementation, data is stored locally on a List (no data
 * persistence of any kind).
 */
public class UserServiceImpl implements UserService {
    
    private List<User> listOfUsers;
    
    public UserServiceImpl() {
        listOfUsers = new LinkedList();
    }
    
    /**
     * Adds new user to database
     * @param user User to be added.
     */
    
    @Override
    public void add(User user) {
        if (!listOfUsers.contains(user)) {
            User user2 = null;
            for (int i = 0; i<(listOfUsers.size()); i++) {
                user2 = listOfUsers.get(i);
                if (user2.getId() == user.getId()) {
                    System.out.printf("\n\tUser id %d already exists, pick another one\n\n", user.getId());
                    return;
                }
            }
            listOfUsers.add(0, user);
        }
    }
    
    /**
     * Reads user information.
     * @param id ID of the User that will be read.
     * @return User instance that matches de given id, or null if there is
     * no User with such id.
     */
     
    @Override
    public User read(int paramId) {
        User user = null;
        for (int i = 0; i<(listOfUsers.size()); i++) {
            user = listOfUsers.get(i);
            if (user.getId() == paramId) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Updates data about the given User
     * @param user User to be updated on database.
     */
    
    @Override
    public void update(User user) {
        if (listOfUsers.contains(user)) {
            User oldUser = listOfUsers.get(listOfUsers.indexOf(user));
            oldUser.setID(user.getId());
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
        }
    }
    
    /**
     * Deletes the specified User
     * @param user User to be deleted from database
     */
    
    @Override
    public void delete(User user) {
        listOfUsers.remove(user);
    }
}

