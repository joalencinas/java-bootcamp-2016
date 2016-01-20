package practice2;

public interface ShoppingCart {
    
    public void addToCart(Product product, int amount);
    public void removeFromCart(Product product);
    public void clearCart();
    public void endPurchase();
    public void getSubTotal();
    public void getAmountOfProducts();
    public void getSubTotal;
}
