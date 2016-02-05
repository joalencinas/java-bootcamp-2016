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
            
            user1.login("pass1");
            user1 = userRepository.save(user1);
            user2.login("pass2");
            user2 = userRepository.save(user2);
            user3.login("pass3");
            user3 = userRepository.save(user3);
            user4.login("pass4");
            user4 = userRepository.save(user4);
            user5.login("pass5");
            user5 = userRepository.save(user5);
            user6.login("pass6");
            user6 = userRepository.save(user6);
            user7.login("pass7");
            user7 = userRepository.save(user7);
            user8.login("pass8");
            user8 = userRepository.save(user8);
            
            
            
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// end::runner[]


