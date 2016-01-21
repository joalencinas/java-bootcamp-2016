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
        if (!listOfProducts.contains(product) && product != null) {
            amountOfProducts++;
            subTotal += product.getPrice();
            listOfProducts.add(0, product);
        }
    }
    
    @Override
    public void removeFromCart(Product product) {
        if (amountOfProducts > 0 && subTotal > 0.0d && listOfProducts.contains(product)) {
            listOfProducts.remove(product);
            amountOfProducts--;
            subTotal -= product.getPrice();
        }
    }
    
    @Override
    public void clearCart() {
        listOfProducts.clear();
        subTotal = 0.0d;
        amountOfProducts = 0;
    }
    
    @Override
    public void endPurchase() {
        Product prod;
        
        System.out.printf("\n\nThe purchase has been processed...\n\n");
        for (int i = 0; i<amountOfProducts; i++) {
            prod = listOfProducts.get(i);
            System.out.printf("\n%s\t\t%f\n", prod.getName(), prod.getPrice());
        }
        System.out.printf("\nTOTAL:\t $%f\n", subTotal);
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
