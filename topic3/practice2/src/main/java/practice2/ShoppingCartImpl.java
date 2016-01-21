package practice2;

import java.util.*;

    /**
     *Local implementation of the ShoppingCart, it doesn't implement
     *any type of data persistence (data is stored locally).
     */
public class ShoppingCartImpl implements ShoppingCart {
    
    private int amountOfProducts;
    private double subTotal;
    private List<Product> listOfProducts;
    
    public ShoppingCartImpl() {
        amountOfProducts = 0;
        subTotal = 0;
        listOfProducts = new LinkedList();
    }
    
    @Override
    public void addToCart(Product product) {
        if (!listOfProducts.contains(product)) {
            amountOfProducts++;
            subTotal += product.getPrice();
            listOfProducts.add(0, product);
        }
    }
    
    @Override
    public void removeFromCart(Product product) {
        
    }
    
    @Override
    public void clearCart() {
    
    }
    
    @Override
    public void endPurchase() {
    
    }
    
    @Override
    public double getSubTotal() {
        return subTotal;
    }
    
    @Override
    public int getAmountOfProducts() {
        return amountOfProducts;
    }
    
    @Override
    public boolean exists(Product product) {
        return listOfProducts.contains(product);
    }
    
    @Override
    public boolean isDuplicated(Product product) {
        return (listOfProducts.indexOf(product) != listOfProducts.lastIndexOf(product));
    }
}
