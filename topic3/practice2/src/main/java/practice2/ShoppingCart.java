package practice2;

/**
 * Interface that represents an abstract ShoppingCart behaviour.
 */
public interface ShoppingCart {
    
    /**
     * Public method used to add products to the cart.
     */
    public void addToCart(Product product);
    
    /**
     * Public method used to remove products from the cart.
     */
    public void removeFromCart(Product product);
    
    /**
     * This method empties the Cart.
     */
    public void clearCart();
    
    /**
     * Ends the Purchase. Depending on the implementation, this method can
     * run some digital payment routine.
     */
    public void endPurchase();
    
    /**
     * Returns the current Shopping cart sub-total, the sum of each product
     * price.
     */
    public double getSubTotal();
    
    /**
     * Returns how many products are there in the Cart.
     */
    public int getAmountOfProducts();
    
    /**
     * Determinates if a given product (instance) exists on the Cart.
     */
    public boolean exists(Product product);
    
    /**
     * Determinates if the specified product (instance) is duplicated on the Cart.
     */
    public boolean isDuplicated(Product product);
}

