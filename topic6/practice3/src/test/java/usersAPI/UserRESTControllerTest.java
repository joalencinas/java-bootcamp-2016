package usersAPI;

import org.junit.*;
import java.util.List;
import java.util.LinkedList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Practice3Application.class)
@EnableMongoRepositories
public class UserRESTControllerTest {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRestController restController;
    
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;
    private static User user5;
    private static User user6;
    private static User user7;
    private static User user8;
    
    @Before
    public void contextLoads() {
//        restController = new UserRestController(userRepository);
        user1 = userRepository.save(new User("jhoeller", "John1", "Smith1"));
        user2 = userRepository.save(new User("dsyer", "John2", "Smith2"));
        user3 = userRepository.save(new User("pwebb", "John3", "Smith3"));
        user4 = userRepository.save(new User("ogierke", "John4", "Smith4"));
        user5 = userRepository.save(new User("rwinch", "John5", "Smith5"));
        user6 = userRepository.save(new User("mfisher", "John6", "Smith6"));
        user7 = userRepository.save(new User("mpollac", "John7", "Smith7"));
        user8 = userRepository.save(new User("jlong", "John8", "Smith8"));
        
    }
    
    @Test
    public void addMethodUnitTest() {
        //Adds an user and asserts if it was successfully added.
        User user9 = new User("jlencinas", "Joa", "Lencinas");
        restController.addUser(user9);
        User user10 = userRepository.findOne("jlencinas");
        Assert.assertNotNull("\n\nFailed, controller didn't add the user\n\n", user10);
        boolean flag = user9.compareTo(user10) == 0;
        Assert.assertTrue("\n\nFailed, controller didn't add the user\n\n", flag);
    }
    
    @Test
    public void deleteMethodUnitTest() {
        //Method deletes correctly the specified user
        User user9 = userRepository.findOne("jlong");
        restController.deleteUser("jlong");
        User user10 = userRepository.findOne("jlong");
        Assert.assertNull("\n\nController didn't delete the user\n\n", user10);
//        restController.addUser(user9);
    }
    
    @Test
    public void updateMethodUnitTest() {
        //Retrieves some user, modifies 2 fields, and Updates it.
        restController.addUser(new User("joalencinas", "Joa", "Lencinas"));
        User user9 = userRepository.findOne("joalencinas");
        user9.setFirstName("Joaquin");
        user9.setLastName("");
        restController.updateUser(user9);
        User user10 = userRepository.findOne("joalencinas");
        boolean flag = user9.compareTo(user10) == 0;
        Assert.assertTrue("\n\nMethod didn't update user correctly\n\n", flag);
        
    }
    
    @Test
    public void readAllMethodUnitTest() {
        //Calls to findAllUers method, asserts if the result is correct.
        List<User> usersRead = restController.readAllUsers();
        Assert.assertNotNull("\n\nReading All users returned null\n\n", usersRead);
        boolean flag = true;
        String[] usernames = {"jhoeller" , "dsyer" , "pwebb" , "ogierke" , "rwinch" ,
             "mfisher" , "mpollac" , "jlong" , "jlencinas" , "joalencinas"};
        for (int i = 0; i<10; i++) {
            flag = flag && userRepository.exists(usernames[i]);
        }
        
        Assert.assertTrue("\n\nSome user is missing in readAll return value", flag);
        
    }
    
    
}











