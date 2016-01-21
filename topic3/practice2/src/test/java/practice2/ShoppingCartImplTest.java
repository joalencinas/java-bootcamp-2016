package practice2;

import org.junit.*;

public class ShoppingCartImplTest {
    
    @Test
    public void cartIsEmptyWhenItIsCreated() {
        ShoppingCart testcart1 = new ShoppingCartImpl();
        boolean flag1 = 0 == testcart1.getAmountOfProducts();
        Assert.assertTrue("\n\nCart should be empty", flag1);
    }
    
    @Test
    public void cartSubTotalIsZeroWhenItIsCreated() {
        ShoppingCart testcart2 = new ShoppingCartImpl();
        boolean flag2 = 0.0d == testcart2.getSubTotal();
        Assert.assertTrue("\n\nCart subtotal should be zero", flag2);
    }
    
    @Test
    public void addingAProductIncrementsTotalAmountOfProducts() {
        ShoppingCart testcart3 = new ShoppingCartImpl();
        Product testproduct1 = new SomeProduct(300.0d);
        Product testproduct2 = new SomeProduct(400.0d);
        Product testproduct3 = new SomeProduct(200.0d);
        testcart3.addToCart(testproduct1);
        Assert.assertTrue("\n\nCart should have 1 product",testcart3.getAmountOfProducts() == 1);
        testcart3.addToCart(testproduct2);
        Assert.assertTrue("\n\nCart should have 2 product",testcart3.getAmountOfProducts() == 2);
        testcart3.addToCart(testproduct3);
        Assert.assertTrue("\n\nCart should have 3 product",testcart3.getAmountOfProducts() == 3);
    }
    
    @Test
    public void addingAProductAddsTheProductPriceToTheSubtotal() {
        ShoppingCart testcart4 = new ShoppingCartImpl();
        Product testproduct4 = new SomeProduct(341.0d);
        Product testproduct5 = new SomeProduct(402.0d);
        Product testproduct6 = new SomeProduct(299.0d);
        testcart4.addToCart(testproduct4);
        boolean flag3 = testcart4.getSubTotal() == 341.0d;
        testcart4.addToCart(testproduct5);
        flag3 = flag3 && (testcart4.getSubTotal() == 341.0d + 402.0d);
        testcart4.addToCart(testproduct6);
        flag3 = flag3 && (testcart4.getSubTotal() == 341.0d + 402.0d + 299.0d);
        
        Assert.assertTrue("\n\nShopping cart doesn't add subtotals correctly", flag3);
    }
    
    @Test
    public void addingAProductAddsTheSpecifiedProductToTheCart() {
        ShoppingCart testcart5 = new ShoppingCartImpl();
        Product testproduct7 = new SomeProduct(241.0d);
        Product testproduct8 = new SomeProduct(1102.0d);
        Product testproduct9 = new SomeProduct(2239.0d);
        testcart5.addToCart(testproduct7);
        boolean flag4 = testcart5.exists(testproduct7);
        testcart5.addToCart(testproduct8);
        flag4 = flag4 && testcart5.exists(testproduct8);
        testcart5.addToCart(testproduct9);
        flag4 = flag4 && testcart5.exists(testproduct9);
        
        Assert.assertTrue("\n\nFailed to add the specified product to the cart", flag4);
    }
    
    @Test
    public void addingAnExistingProductInstanceDoesNotDuplicateIt() {
        ShoppingCart testcart6 = new ShoppingCartImpl();
        Product testproduct10 = new SomeProduct(221.0d);
        Product testproduct11 = new SomeProduct(902.0d);
        Product testproduct12 = new SomeProduct(1004.0d);
        testcart6.addToCart(testproduct10);
        testcart6.addToCart(testproduct11);
        testcart6.addToCart(testproduct12);
        testcart6.addToCart(testproduct10);
        boolean flag5 = testcart6.isDuplicated(testproduct10);
        testcart6.addToCart(testproduct11);
        flag5 = flag5 || testcart6.isDuplicated(testproduct11);
        testcart6.addToCart(testproduct12);
        flag5 = flag5 || testcart6.isDuplicated(testproduct12);
        
        Assert.assertFalse("\n\nCart duplicated some product when it was added twice", flag5);
    }
    
    @Test
    public void clearAnEmptyCartDoesNothing() {
        ShoppingCart testcart12 = new ShoppingCartImpl();
        testcart12.clearCart();
        boolean flag10 = testcart12.getAmountOfProducts() == 0 && testcart12.getSubTotal() == 0.0d;
        Assert.assertTrue("Cart should be as new", flag10);
    }
    
    @Test
    public void clearTheCartDecrementsProductsCountToZero() {
        ShoppingCart testcart7 = new ShoppingCartImpl();
        Product testproduct13 = new SomeProduct(221.0d);
        Product testproduct14 = new SomeProduct(9222.0d);
        Product testproduct15 = new SomeProduct(6504.0d);
        testcart7.addToCart(testproduct13);
        testcart7.addToCart(testproduct14);
        testcart7.addToCart(testproduct15);
        testcart7.clearCart();
        Assert.assertTrue("\n\nCart should have zero products", testcart7.getAmountOfProducts() == 0);
    }
    
    @Test
    public void clearTheCartDecrementsSubTotalToZero() {
        ShoppingCart testcart8 = new ShoppingCartImpl();
        Product testproduct16 = new SomeProduct(14521.0d);
        Product testproduct17 = new SomeProduct(8765.0d);
        Product testproduct18 = new SomeProduct(1022.0d);
        testcart8.addToCart(testproduct16);
        testcart8.addToCart(testproduct17);
        testcart8.addToCart(testproduct18);
        testcart8.clearCart();
        
        Assert.assertTrue("\n\nSubTotal should be zero after clear", testcart8.getSubTotal() == 0.0d);
    }
    
    @Test
    public void clearTheCartRemovesAllProducts() {
        ShoppingCart testcart9 = new ShoppingCartImpl();
        Product testproduct19 = new SomeProduct(1212.5d);
        Product testproduct20 = new SomeProduct(562.5d);
        Product testproduct21 = new SomeProduct(8712.5d);
        testcart9.addToCart(testproduct19);
        testcart9.addToCart(testproduct20);
        testcart9.addToCart(testproduct21);
        testcart9.clearCart();
        
        boolean flag6 = testcart9.exists(testproduct19);
        flag6 = flag6 || testcart9.exists(testproduct20);
        flag6 = flag6 || testcart9.exists(testproduct21);
        
        Assert.assertFalse("\n\nCart still contains some product after clear", flag6);
    }
    
    @Test
    public void removeDecrementsProductsCountByOne() {
        ShoppingCart testcart10 = new ShoppingCartImpl();
        Product testproduct22 = new SomeProduct(4574.9d);
        Product testproduct23 = new SomeProduct(924.4d);
        Product testproduct24 = new SomeProduct(1234.1d);
        testcart10.addToCart(testproduct22);
        testcart10.addToCart(testproduct23);
        testcart10.addToCart(testproduct24);
        int amount = testcart10.getAmountOfProducts();
        testcart10.removeFromCart(testproduct22);
        int newAmount = testcart10.getAmountOfProducts();
        Assert.assertTrue("\n\nCart should have 1 less product now", newAmount + 1 == amount); 
        
    }
    
    @Test
    public void removeFromEmptyCartDoesNothing() {
        ShoppingCart testcart11 = new ShoppingCartImpl();
        testcart11.removeFromCart(new SomeProduct(1234.5d));
        boolean flag9 = testcart11.getAmountOfProducts() == 0 && testcart11.getSubTotal() == 0.0d;
        Assert.assertTrue("Cart should be as new", flag9);
    }
    
    @Test
    public void removeDecrementsProductPriceFromCartSubTotal() {
        ShoppingCart testcart13 = new ShoppingCartImpl();
        Product testproduct25 = new SomeProduct(130241.0d);
        Product testproduct26 = new SomeProduct(10241.0d);
        Product testproduct27 = new SomeProduct(1230241.0d);
        Product testproduct28 = new SomeProduct(13541.0d);
        testcart13.addToCart(testproduct25);
        testcart13.addToCart(testproduct26);
        testcart13.addToCart(testproduct27);
        testcart13.addToCart(testproduct28);
        
        double subtotal = testcart13.getSubTotal();
        testcart13.removeFromCart(testproduct25);
        boolean flag7 = testcart13.getSubTotal() == subtotal - 130241.0d;
        testcart13.removeFromCart(testproduct26);
        flag7 = flag7 && testcart13.getSubTotal() == subtotal - 130241.0d - 10241.0d;
        testcart13.removeFromCart(testproduct27);
        flag7 = flag7 && testcart13.getSubTotal() == subtotal - 130241.0d - 10241.0d - 1230241.0d;
        testcart13.removeFromCart(testproduct28);
        flag7 = flag7 && testcart13.getSubTotal() == subtotal - 130241.0d - 10241.0d - 1230241.0d - 13541.0d;
        
        Assert.assertTrue("\n\nRemoving products didn't decrement subtotal correctly", flag7);
    }
    
    @Test
    public void removeRemovesTheSpecifiedProductFromCart() {
        ShoppingCart testcart14 = new ShoppingCartImpl();
        Product testproduct29 = new SomeProduct(30241.0d);
        Product testproduct30 = new SomeProduct(22241.0d);
        Product testproduct31 = new SomeProduct(1241.0d);
        Product testproduct32 = new SomeProduct(111.0d);
        testcart14.addToCart(testproduct29);
        testcart14.addToCart(testproduct30);
        testcart14.addToCart(testproduct31);
        testcart14.addToCart(testproduct32);
        
        testcart14.removeFromCart(testproduct29);
        boolean flag8 = testcart14.exists(testproduct29);
        testcart14.removeFromCart(testproduct30);
        flag8 = flag8 || testcart14.exists(testproduct30);
        testcart14.removeFromCart(testproduct31);
        flag8 = flag8 || testcart14.exists(testproduct31);
        testcart14.removeFromCart(testproduct32);
        flag8 = flag8 || testcart14.exists(testproduct32);
        
        Assert.assertFalse("Some product was present after removal", flag8);
    }
    
    @Test
    public void removeANonExistingProductDoesNothing() {
        ShoppingCart testcart15 = new ShoppingCartImpl();
        Product testproduct33 = new SomeProduct(11241.0d);
        Product testproduct34 = new SomeProduct(25241.0d);
        Product testproduct35 = new SomeProduct(1341.0d);
        Product testproduct36 = new SomeProduct(151.0d);
        testcart15.addToCart(testproduct33);
        testcart15.addToCart(testproduct34);
        testcart15.addToCart(testproduct35);
        
        testcart15.removeFromCart(testproduct36);
        boolean flag9 = testcart15.getAmountOfProducts() == 3;
        flag9 = flag9 && testcart15.getSubTotal() == 11241.0d + 25241.0d + 1341.0d;
        
        Assert.assertTrue("Removal of a non existing Product changed the cart", flag9);
    }
    
    @Test
    public void endPurchaseTest() {
        ShoppingCart testcart16 = new ShoppingCartImpl();
        Product testproduct37 = new SomeProduct(11241.0d, "Shoe", "A very expensive shoe");
        Product testproduct38 = new SomeProduct(25241.0d, "Car", "Renault 12, model 1998" );
        Product testproduct39 = new SomeProduct(1341.0d, "Chair", "Really comfortable chair");
        Product testproduct40 = new SomeProduct(151.0d, "T-Shirt", "A nice t-shirt");
        testcart16.addToCart(testproduct37);
        testcart16.addToCart(testproduct38);
        testcart16.addToCart(testproduct39);
        testcart16.addToCart(testproduct40);
        
        testcart16.endPurchase();
        
        Assert.assertTrue(true);
    }
}






