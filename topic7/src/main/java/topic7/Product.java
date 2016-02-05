package topic7;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;


@Api
public class Product implements Comparable<Product> {
    
    @Id
    private final String name;
    
    private int price;
    private String category;
    private String description;
    
    public Product(String name, int price, String category, String description) {
        this.name = name;
        this.price = price;
        if (price < 0)
            this.price = 0;
        this.category = category;
        this.description = description;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns Product's unique name", required = true)
    public String getName() {
        return name;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Sets product's price to the given parameter only " + 
        "if it is positive. Sets price to 0 otherwise", required = true)
    public void setPrice(int price) {
        if (price > 0) {
            this.price = price;
        }
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns the price", required = true)
    public int getPrice() {
        return price;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Sets the category. Doesn't check the parameter", required = true)
    public void setCategory(String category) {
        this.category = category;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns the category", required = true)
    public String getCategory() {
        return category;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Sets the description", required = true)
    public void setDescription(String description) {
        this.description = description;
    }
    
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Returns product's description", required = true)
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Product data: Name = %s, Price = %d, Category = %s, Description = %s   ",
            name, price, category, description);
    }
    
    @Override
    public int compareTo(Product anotherProduct) {
        int result = 1;
        if (this.name.compareTo(anotherProduct.name) == 0 && 
              price == anotherProduct.price &&
                this.category.compareTo(anotherProduct.category) == 0 &&
                  this.description.compareTo(anotherProduct.description) == 0) {
            result = 0;
        } else {
            result = this.name.compareTo(anotherProduct.name);
        }
        return result;
    }
}



