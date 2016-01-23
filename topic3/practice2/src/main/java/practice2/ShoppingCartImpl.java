package practice2;

import java.util.*;

    /**
     *Local implementation of the ShoppingCart, it doesn't implement
     *any type of data persistence (data is stored locally).
     */
public class ShoppingCartImpl implements ShoppingCart {
    /**
     * Private field. It represents how many Products are there in the Cart.
     */
    private int amountOfProducts;
    
    /**
     * Private field, represents the sum of all the contained Product's prices.
     */
    private double subTotal;
    
    /**
     * Private field that contains the references to the Product objects
     * stored on a generic type list.
     */
    private List<Product> listOfProducts;
    
    /**
     * On this particular implementation, constructor inicializes on zero
     * the fields amountOfProducts and subTotal, and the field listOfProducts
     * is implemented as a LinkedList. Therefore, the data is stored
     * locally (no data persistence routine is used).
     */
    public ShoppingCartImpl() {
        amountOfProducts = 0;
        subTotal = 0.0d;
        listOfProducts = new LinkedList();
    }
    
    /**
     * This method adds a specified product to the cart, only if the Product
     * object is not null and is not present already in the Cart.
     */
    @Override
    public void addToCart(Product product) {
        if (!listOfProducts.contains(product) && product != null) {
            amountOfProducts++;
            subTotal += product.getPrice();
            listOfProducts.add(0, product);
        }
    }
    
    /**
     * Removes the specified Product from the Cart. If the specified instance
     * doesn't exist on the cart, or the cart is empty, it does nothing.
     */
    @Override
    public void removeFromCart(Product product) {
        if (amountOfProducts > 0 && subTotal > 0.0d && listOfProducts.contains(product)) {
            listOfProducts.remove(product);
            amountOfProducts--;
            subTotal -= product.getPrice();
        }
    }
    
    /**
     * Removes all Products from the Cart, and sets it like it was brand new.
     */
    @Override
    public void clearCart() {
        listOfProducts.clear();
        subTotal = 0.0d;
        amountOfProducts = 0;
    }
    
    /**
     * Virtually "ends" the purchase, in this particular implementation it
     * just prints some information about the purchase: each Product
     * price and name, and the Purchase total.
     */
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
    
    /**
     * Returns the Shopping cart sub-total
     */
    @Override
    public double getSubTotal() {
        return subTotal;
    }
    
    /**
     * Returns how many products are there in the cart
     */
    @Override
    public int getAmountOfProducts() {
        return amountOfProducts;
    }
    
    /**
     * Returs true if the specified product instance exists on the cart.
     */
    @Override
    public boolean exists(Product product) {
        return listOfProducts.contains(product);
    }
    
    /**
     * Returns true if the given product instance is duplicated on the Cart, or
     * if it doesn't exist on the cart at all. Returns false otherwise.
     */
    @Override
    public boolean isDuplicated(Product product) {
        return (listOfProducts.indexOf(product) != listOfProducts.lastIndexOf(product));
    }
}
