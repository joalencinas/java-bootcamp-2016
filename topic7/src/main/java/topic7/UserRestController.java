package topic7;

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
import io.swagger.annotations.Api;


@RestController
@RequestMapping("/users")
@Api(value = "User", description = "All user's operations")
class UserRestController {
    
    private final UserRepository userRepository;
    
    
    @ApiOperation(value = "addUser", nickname = "addUser")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "User object to add", 
            required = true, dataType = "User.class", paramType = "Request Body")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    User addUser(@RequestBody User user) {
        if (!this.userRepository.exists(user.getUsername())) {
            User result = userRepository.save(user);
            return result;
        } else {
            System.out.printf("\n\nUsername %s already taken, choose another one\n\n", user.getUsername());
            return null;
        }
    }
    
    @ApiOperation(value = "readAllUsers", nickname = "readAll")
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
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
        @ApiImplicitParam(name = "user", value = "User object to update", 
            required = true, dataType = "User.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 

    User updateUser(@RequestBody User updatedUser) {
        if (updatedUser.isLoggedIn()) {
            if (!userRepository.exists(updatedUser.getUsername())) {
                throw new RuntimeException("UPDATE FAILED: user not found");
            } else {
                return userRepository.save(updatedUser);
            }
        } else {
            System.out.println("\n\nUser is not logged in! Log in first\n\n");
            return null;
        }
    }
    
    @ApiOperation(value = "readUserByUsername", nickname = "readByUsername")
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "Username to query", 
            required = true, dataType = "String", paramType = "Path Variable")
      })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    User readUserByUsername(@PathVariable String username) {
        User user = userRepository.findOne(username);
        if (user != null) {
            if (user.isLoggedIn()) {
                return user;
            } else {
                System.out.println("\n\nUser is not logged in! Log in first\n\n");
                return null;
            }
        } else {
            System.out.println("\n\nUser not found\n\n");
            return null;
        }
    }

    @ApiOperation(value = "deleteUser", nickname = "deleteUser")
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "username of the User object to delete", 
            required = true, dataType = "String", paramType = "Path Variable")
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
            if (result.isLoggedIn()) {
                userRepository.delete(result);
            } else {
                System.out.println("\n\nUser is not logged in! Log in first\n\n");
            }
        } else {
            System.out.printf("\n\nUser to delete \"%s\" not found\n\n", username);
        }
    }

    
    @ApiOperation(value = "loginUser", nickname = "User Login")
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "User's username", 
            required = true, dataType = "String", paramType = "Request Body"),
        @ApiImplicitParam(name = "password", value = "User's password",
            required = true, dataType = "String", paramType = "Request Body")
        })
        
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    boolean loginUser(@RequestBody String username, @RequestBody String password) {
        User user = userRepository.findOne(username);
        boolean result = false;
        if (user != null) {
            result = user.login(password);
            if (result) {
                System.out.printf("\n\nUser \"%s\" logged in successfully\n\n", username);
                userRepository.save(user);
            } else {
                System.out.printf("\n\nWrong password\n\n");
            }
        } else {
            System.out.printf("\n\nUser \"%s\" doesn't exist\n\n", username);
        }
        return result;
    }


    
    @ApiOperation(value = "logoutUser", nickname = "User logout")
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "User object to logout", 
            required = true, dataType = "User.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 

    void logoutUser(@RequestBody User user) {
        if (user != null) {
            if (userRepository.exists(user.getUsername())) {
                user.logout();
                userRepository.save(user);
            } else {
                System.out.println("\n\nLOGOUT FAILED: user not found\n\n");
            }
        }
    }
    
    
        
    @ApiOperation(value = "addProductToCart", nickname = "Add to Cart")
    @RequestMapping(value = "/{username}/cart", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "Name of the User account", 
            required = true, dataType = "String", paramType = "Path variable"),
        @ApiImplicitParam(name = "product", value = "Product to add", 
            required = true, dataType = "Product.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    User addProductToCart(@PathVariable String username, @RequestBody Product product) {
        User user = userRepository.findOne(username);
        if (user != null) {
            if (user.isLoggedIn()) {
                List<Product> list = user.getShoppingCart();
                list.add(product);
                user.setShoppingCart(list);
                return userRepository.save(user);
            } else {
                System.out.println("\n\nUser is not logged in! Log in first\n\n");
                return null;
            }
        } else {
            System.out.printf("\n\nUser %s not found\n\n", username);
            return null;
        }
    }
    
    
    @ApiOperation(value = "getAllProductFromUserCart", nickname = "getAllFromUser")
    @RequestMapping(value = "/{username}/cart", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "User name", 
            required = true, dataType = "String", paramType = "Path Variable")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    List<Product> getAllProductsFromUserCart(@PathVariable String username) {
        User user = userRepository.findOne(username);
        if (user != null) {
            if (user.isLoggedIn()) {
                return user.getShoppingCart();
            } else {
                System.out.println("\n\nUser is not logged in! Log in first\n\n");
                return null;
            }
        } else {
            System.out.printf("\n\nUser %s not found\n\n", username);
            return null;
        }
    }
    
    
    @ApiOperation(value = "clearUserCart", nickname = "clearCart")
    @RequestMapping(value = "/{username}/cart/clear", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "User name whose cart will be cleared", 
            required = true, dataType = "String", paramType = "Path Variable")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    List<Product> clearUserCart(@PathVariable String username) {
        User user = userRepository.findOne(username);
        if (user != null) {
            if (user.isLoggedIn()) {
                List<Product> result = user.getShoppingCart();
                user.setShoppingCart(new LinkedList());
                userRepository.save(user);
                return result;
            } else {
                System.out.println("\n\nUser is not logged in! Log in first\n\n");
                return null;
            }
        } else {
            System.out.printf("\n\nUser %s not found\n\n", username);
            return null;
        }
    }
    
    
    @ApiOperation(value = "purchaseCart", nickname = "purchaseCart")
    @RequestMapping(value = "/{username}/cart/purchase")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "Username of the user that will purchase", 
            required = true, dataType = "String", paramType = "Path Variable")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    int purchaseCart(@PathVariable String username) {
        User user = userRepository.findOne(username);
        int result = 0;
        if (user != null) {
            if (user.isLoggedIn()) {
                List<Product> list = user.getShoppingCart();
                for (int i = 0; i<list.size(); i++) {
                    result += list.get(i).getPrice();
                }
                System.out.printf("\n\nProducts purchased correctly\n\n");
                return result;
            } else {
                System.out.printf("\n\nUser %s not logged in!! Log in to purchase\n\n", username);
                return -1;
            }
        } else {
            System.out.printf("\n\nUser %s not found\n\n", username);
            return -1;
        }
    }
    
    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}


