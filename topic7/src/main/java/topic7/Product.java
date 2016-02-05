package topic7;

import org.springframework.data.annotation.Id;


public class Product implements Comparable<Product> {
    
    @Id
    private final String name;
    
    private int price;
    private String category;
    private String description;
    
    public Product(String name, int price, String category, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
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



