package practice2;


public class SomeProduct implements Product {
    
    private double prodPrice;
    private String prodName;
    private String prodDescription;
    
    public SomeProduct(double price) {
        
        if (price > 0) {
            prodPrice = price;
        } else {
            throw new RuntimeException("Product price must be a positive value");
        }
    }

    public SomeProduct(double price, String name, String description) {
        
        if (price > 0) {
            prodPrice = price;
            prodName = name;
            prodDescription = description;
        } else {
            throw new RuntimeException("Product price must be a positive value");
        }
    }
    
    @Override
    public void setPrice(double price) {
        if (price > 0) {
            prodPrice = price;
        } else {
            throw new RuntimeException("Price must be a positive value");
        }
    }
    
    @Override
    public double getPrice() {
        return prodPrice;
    }
    
    @Override
    public String getName() {
        return prodName;
    }
    
    
    @Override
    public void setName(String name) {
        prodName = name;
    }
    
    
    @Override
    public String getDescription() {
        return prodDescription;
    }
    
    @Override
    public void setDescription(String description) {
        prodDescription = description;
    }
    
}











