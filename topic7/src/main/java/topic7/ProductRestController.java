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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;


@RestController
@RequestMapping("/products")
@Api(value = "Product", description = "All product's operations")
class ProductRestController {
    
    private final ProductRepository productRepository;
    
    
    
    @ApiOperation(value = "addProduct", nickname = "Create a new Product")
    @RequestMapping(method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product", value = "Product to create", 
            required = true, dataType = "Product.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    Product addProduct(@RequestBody Product product) {
        if (!productRepository.exists(product.getName())) {
            Product result = productRepository.save(product);
            return result;
        } else {
            System.out.printf("\n\nProduct %s already exists, choose another name\n\n", product.getName());
            return null;
        }
    }
    
    
    @ApiOperation(value = "getAllProducts", nickname = "getAllProducts")
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    
    @ApiOperation(value = "getProductByName", nickname = "getProductByName")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "Name of the product to find", 
            required = true, dataType = "String", paramType = "Path Variable")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    Product getProductByName(@PathVariable String name) {
        Product product = productRepository.findOne(name);
        if (product != null) {
            return product;
        } else {
            System.out.printf("\n\nProduct %s not found \n\n", name);
            return null;
        }
    }
    
    
    @ApiOperation(value = "getProductsByCategory", nickname = "getByCategory")
    @RequestMapping(value = "/byCategory/{category}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "category", value = "Category to find products", 
            required = true, dataType = "String", paramType = "Path Variable")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    List<Product> getProductsByCategory(@PathVariable String category) {
        if (category != "" && category != null) {
            return productRepository.findByCategory(category);
        } else {
            return productRepository.findAll();
        }
    }
    
    
    @ApiOperation(value = "updateProduct", nickname = "updateProduct")
    @RequestMapping(method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product", value = "User object to update", 
            required = true, dataType = "Product.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    Product updateProduct(@RequestBody Product newProduct) {
        if (productRepository.exists(newProduct.getName())) {
            return productRepository.save(newProduct);
        } else {
            System.out.printf("\n\n Product %s doesn't exist\n\n", newProduct.getName());
            return null;
        }
    }
    
    
    @ApiOperation(value = "deleteProduct", nickname = "deleteProduct")
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "product", value = "Product to delete from repository", 
            required = true, dataType = "Product.class", paramType = "Request Body")
        })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")}) 
    
    void deleteProduct(@RequestBody Product product) {
        productRepository.delete(product);
    }
    
    @Autowired
    ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
}



