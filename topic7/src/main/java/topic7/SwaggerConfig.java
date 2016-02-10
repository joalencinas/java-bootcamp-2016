package topic7;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableSwagger2
@ComponentScan("topic7")
public class SwaggerConfig {
    
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

