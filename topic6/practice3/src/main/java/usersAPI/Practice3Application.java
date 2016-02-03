// tag::runner[]
package usersAPI;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("usersAPI")
public class Practice3Application {
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return evt -> {
            User user1 = userRepository.save(new User("jhoeller", "John1", "Smith1"));
            User user2 = userRepository.save(new User("dsyer", "John2", "Smith2"));
            User user3 = userRepository.save(new User("pwebb", "John3", "Smith3"));
            User user4 = userRepository.save(new User("ogierke", "John4", "Smith4"));
            User user5 = userRepository.save(new User("rwinch", "John5", "Smith5"));
            User user6 = userRepository.save(new User("mfisher", "John6", "Smith6"));
            User user7 = userRepository.save(new User("mpollac", "John7", "Smith7"));
            User user8 = userRepository.save(new User("jlong", "John8", "Smith8"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Practice3Application.class, args);
    }
    
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("usersAPI")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/usersAPI.*"))
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST User Register API")
                .description("Simple Spring REST user registration API using Spring Data with mongoDB")
                .termsOfServiceUrl("")
                .contact("Joaquin Lencinas")
                .license("")
                .licenseUrl("")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
// end::runner[]


@RestController
@RequestMapping("/users")
class UserRestController {
    
    private final UserRepository userRepository;
    
    
    @ApiOperation(value = "addUser", nickname = "addUser")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "User object to add", required = true, dataType = "User.class", paramType = "Request Body")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity<?>.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    ResponseEntity<?> addUser(@RequestBody User user) {
        if (!this.userRepository.exists(user.getUsername())) {
            User result = userRepository.save(new User(user.getUsername(),
                user.getFirstName(), user.getLastName()));
            
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}")
                    .buildAndExpand(result.getUsername()).toUri());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
            
        } else {
            System.out.printf("\n\nUsername %s already taken, choose another one\n\n", user.getUsername());
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.IM_USED);
        }
    }
    
    @ApiOperation(value = "readAllUsers", nickname = "readAll")
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = List<User>.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    List<User> readAllUsers() {
        return userRepository.findAll();
    }
    
    @ApiOperation(value = "updateUser", nickname = "updateUser")
    @RequestMapping(method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "User object to update", required = true, dataType = "User.class", paramType = "Request Body")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 

    User updateUser(@RequestBody User updatedUser) {
        User result = userRepository.findOne(updatedUser.getUsername());
        if (result == null) {
            throw new RuntimeException("UPDATE FAILED: user not found");
        }
        result.setFirstName(updatedUser.getFirstName());
        result.setLastName(updatedUser.getLastName());
        return userRepository.save(result);
    }
    
    @ApiOperation(value = "readUserByUsername", nickname = "readByUsername")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "Username to query", required = true, dataType = "String", paramType = "Path Variable")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    User readUserByUsername(@PathVariable String username) {
        return userRepository.findOne(username);
    }

    @ApiOperation(value = "deleteUser", nickname = "deleteUser")
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "username of the User object to delete", required = true, dataType = "String", paramType = "Path Variable")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    void deleteUser(@PathVariable String username) {
        User result = userRepository.findOne(username);
        if (result != null) {
            userRepository.delete(result);
        } else {
            System.out.printf("\n\nUser to delete \"%s\" not found\n\n", username);
        }
    }


    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}


