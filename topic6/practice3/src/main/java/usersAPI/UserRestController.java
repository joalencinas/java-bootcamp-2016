package usersAPI;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User user) {
        if (!this.userRepository.exists(user.getUsername())) {
            User result = userRepository.save(new User(user.getUsername(),
                user.getFirstName(), user.getLastName()));
            
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                    .buildAndExpand(result.getUsername()).toUri());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
            
        } else {
            System.out.println("\n\nUsername already taken, choose another one\n\n");
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.IM_USED);
        }
        
    }

    @RequestMapping(method = RequestMethod.GET)
    List<User> readAllUsers() {
        
        return userRepository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    User updateUser(@RequestBody User updatedUser) {
        User result = userRepository.findOne(updatedUser.getUsername());
        if (result == null) {
            throw new RuntimeException("UPDATE FAILED: user not found");
        }
        result.setFirstName(updatedUser.getFirstName());
        result.setLastName(updatedUser.getLastName());
        return userRepository.save(result);
    }
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    User readUserByUsername(@PathVariable String username) {
        return userRepository.findOne(username);
    }
    
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    void deleteUser(@RequestBody User user) {
        User result = userRepository.findOne(user.getUsername());
        if (result == null) {
            throw new RuntimeException("DELETE FAILED: user not found");
        }
        userRepository.delete(result);
    }


    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
