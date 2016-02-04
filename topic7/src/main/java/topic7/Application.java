// tag::runner[]
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

@SpringBootApplication
@EnableSwagger2
@ComponentScan("topic7")
public class Application {
    @Bean
    CommandLineRunner init(UserRepository userRepository, 
                            ProductRepository productRepository) {
        return evt -> {
            User user1 = userRepository.save(new User("jhoeller", "pass1", "John1", "Smith1"));
            User user2 = userRepository.save(new User("dsyer", "pass2", "John2", "Smith2"));
            User user3 = userRepository.save(new User("pwebb", "pass3", "John3", "Smith3"));
            User user4 = userRepository.save(new User("ogierke", "pass4", "John4", "Smith4"));
            User user5 = userRepository.save(new User("rwinch", "pass5", "John5", "Smith5"));
            User user6 = userRepository.save(new User("mfisher", "pass6", "John6", "Smith6"));
            User user7 = userRepository.save(new User("mpollac", "pass7", "John7", "Smith7"));
            User user8 = userRepository.save(new User("jlong", "pass8", "John8", "Smith8"));
            
            Product product1 = productRepository.save(new Product("car1", 10000, "transport", "cheap car"));
            Product product2 = productRepository.save(new Product("car2", 40100, "transport", "cheap car"));
            Product product3 = productRepository.save(new Product("car3", 26000, "transport", "cheap car"));
            Product product4 = productRepository.save(new Product("car4", 10030, "transport", "cheap car"));
            Product product5 = productRepository.save(new Product("car5", 90312, "transport", "cheap car"));
            Product product6 = productRepository.save(new Product("car6", 11000, "transport", "cheap car"));
            Product product7 = productRepository.save(new Product("car7", 10012, "transport", "cheap car"));
            Product product8 = productRepository.save(new Product("car8", 20100, "transport", "cheap car"));
            Product product9 = productRepository.save(new Product("car9", 15500, "transport", "cheap car"));
            
            Product product11 = productRepository.save(new Product("shoe1", 10000, "clothing", "cheap shoe"));
            Product product12 = productRepository.save(new Product("shoe2", 10000, "clothing", "cheap shoe"));
            Product product13 = productRepository.save(new Product("shoe3", 10000, "clothing", "cheap shoe"));
            Product product14 = productRepository.save(new Product("shoe4", 10000, "clothing", "cheap shoe"));
            Product product15 = productRepository.save(new Product("shoe5", 10000, "clothing", "cheap shoe"));
            Product product16 = productRepository.save(new Product("shoe6", 10000, "clothing", "cheap shoe"));
            Product product17 = productRepository.save(new Product("shoe7", 10000, "clothing", "cheap shoe"));
            Product product18 = productRepository.save(new Product("shoe8", 10000, "clothing", "cheap shoe"));
            Product product19 = productRepository.save(new Product("shoe9", 10000, "clothing", "cheap shoe"));
            
            
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("topic7")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/ShoppingCartAPI.*"))
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Shopping Cart API")
                .description("Simple Spring REST Shopping cart API using Spring Data with mongoDB")
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
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    ResponseEntity<?> addUser(@RequestBody User user) {
        if (!this.userRepository.exists(user.getUsername())) {
            User result = userRepository.save(user);
            
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
        @ApiImplicitParam(name = "user", value = "User object to update", required = true, dataType = "User.class", paramType = "Request Body")
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
        @ApiImplicitParam(name = "username", value = "Username to query", required = true, dataType = "String", paramType = "Path Variable")
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
    
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    boolean loginUser(@RequestBody String username, @RequestBody String password) {
        User user = userRepository.findOne(username);
        boolean result = false;
        if (user != null) {
            result = user.login(password);
            if (result) {
                System.out.printf("\n\nUser \"%s\" logged in successfully\n\n", username);
            } else {
                System.out.printf("\n\nWrong password\n\n");
            }
        } else {
            System.out.printf("\n\nUser \"%s\" doesn't exist\n\n", username);
        }
        userRepository.save(user);
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    void logoutUser(@RequestBody User user) {
        if (userRepository.exists(user.getUsername())) {
            user.logout();
            userRepository.save(user);
        } else {
            System.out.println("\n\nLOGOUT FAILED: user not found\n\n");
        }
    }
    
    
    @RequestMapping(value = "/{username}/cart", method = RequestMethod.PUT)
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
    
    @RequestMapping(value = "/{username}/cart", method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/{username}/cart/clear", method = RequestMethod.PUT)
    List<Product> cleanUserCart(@PathVariable String username) {
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
    
    @Autowired
    UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

@RestController
@RequestMapping("/products")
class ProductRestController {
    
    private final ProductRepository productRepository;
    
    
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addProduct(@RequestBody Product product) {
        if (!productRepository.exists(product.getName())) {
            Product result = productRepository.save(product);
            
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{category}")
                    .buildAndExpand(result.getCategory()).toUri());
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
            
        } else {
            System.out.printf("\n\nProduct %s already exists, choose another name\n\n", product.getName());
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.IM_USED);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    Product getProductByName(@PathVariable String name) {
        Product product = productRepository.findOne(name);
        if (product != null) {
            return product;
        } else {
            System.out.printf("\n\nProduct %s not found \n\n", name);
            return null;
        }
    }
    
    @RequestMapping(value = "/byCategory/{category}", method = RequestMethod.GET)
    List<Product> getProductsByCategory(@PathVariable String category) {
        if (category != "") {
            return productRepository.findByCategory(category);
        } else {
            return productRepository.findAll();
        }
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    Product updateProduct(@RequestBody Product newProduct) {
        if (productRepository.exists(newProduct.getName())) {
            return productRepository.save(newProduct);
        } else {
            System.out.printf("\n\n Product %s doesn't exist\n\n", newProduct.getName());
            return null;
        }
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    void deleteProduct(@RequestBody Product product) {
        productRepository.delete(product);
    }
    
    @Autowired
    ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
}



