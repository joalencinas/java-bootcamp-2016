package practice5;

import org.junit.*;

public class UserServiceImplTest {
    
    @Test
    public void addingNewUserAddsTheSpecifiedUser() {
        UserService servicetest = new UserServiceImpl();
        User testuser1 = new User(2);
        servicetest.add(testuser1);
        
        User testuser2 = servicetest.read(2);
        Assert.assertSame("User should be on the list",testuser1, testuser2);
    }
    
    @Test
    public void readingANonExistingUserDoesNothing() {
        UserService servicetest = new UserServiceImpl();
        User testuser1 = new User(4);
        User testuser2 = new User(6);
        servicetest.add(testuser1);
        servicetest.add(testuser2);
        User testuser3 = servicetest.read(7);
        
        Assert.assertNull("Reading an unexisting user should return null", testuser3);
    }
    
    @Test
    public void deleteDeletesTheSpecifiedUser() {
        UserService servicetest = new UserServiceImpl();
        User testuser1 = new User(8);
        User testuser2 = new User(9);
        servicetest.add(testuser1);
        servicetest.add(testuser2);
        
        servicetest.delete(testuser1);
        User testuser3 = servicetest.read(8);
        
        Assert.assertNull("Service didn't deleted the user specified", testuser3);
    }
    
    @Test
    public void updateUpdatesTheSpecifiedUser() {
        UserService servicetest = new UserServiceImpl();
        User testuser = new User(10);
        servicetest.add(testuser);
        testuser.setName("name");
        testuser.setEmail("email");
        
        User testuser2 = servicetest.read(10);
        servicetest.update(testuser);
        Assert.assertEquals("Service didn't updated correctly the user", testuser2, testuser);
    }
    
    @Test
    public void deleteAnUnexistingUserDoesNothing() {
        UserService servicetest = new UserServiceImpl();
        User testuser = new User(12);
        User testuser2 = new User(11);
        servicetest.add(testuser);
        servicetest.delete(testuser2);
        User testuser3 = servicetest.read(12);
        Assert.assertNotNull("removing unexisting User changed the List", testuser3);
    }
    
    @Test
    public void deleteRemovesTheSpecifiedUser() {
        UserService servicetest = new UserServiceImpl();
        User testuser = new User(12);
        User testuser2 = new User(11);
        servicetest.add(testuser);
        servicetest.add(testuser2);
        servicetest.delete(testuser2);
        
        User testuser3 = servicetest.read(11);
        
        Assert.assertNull("This user shouldn't exist anymore", testuser3);
    }
    
    
}
