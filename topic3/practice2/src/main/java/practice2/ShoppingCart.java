package practice2;

public interface ShoppingCart {
    
    public void addToCart(Product product);
    public void removeFromCart(Product product);
    public void clearCart();
    public void endPurchase();
    public double getSubTotal();
    public int getAmountOfProducts();
    public boolean exists(Product product);
    public boolean isDuplicated(Product product);
}

