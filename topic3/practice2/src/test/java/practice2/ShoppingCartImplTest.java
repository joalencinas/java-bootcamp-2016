package practice2;

import org.junit.*;

public class ShoppingCartImplTest {
    
    @Test
    public void cartIsEmptyWhenItIsCreated() {
        ShoppingCart testcart1 = new ShoppingCartImpl();
        boolean flag1 = 0 == testcart1.getAmountOfProducts();
        Assert.assertTrue("Cart should be empty", flag1);
    }
    
    @Test
    public void cartSubTotalIsZeroWhenItIsCreated() {
        ShoppingCart testcart2 = new ShoppingCartImpl();
        boolean flag2 = 0.0d == testcart2.getSubTotal();
        Assert.assertTrue("Cart subtotal should be zero", flag2);
    }
    
    @Test
    public void addingAProductIncrementsTotalAmountOfProducts() {
        ShoppingCart testcart3 = new ShoppingCartImpl();
        Product testproduct1 = new SomeProduct(300.0d);
        Product testproduct2 = new SomeProduct(400.0d);
        Product testproduct3 = new SomeProduct(200.0d);
        testcart3.addToCart(testproduct1);
        Assert.assertTrue("Cart should have 1 product",testcart3.getAmountOfProducts() == 1);
        testcart3.addToCart(testproduct2);
        Assert.assertTrue("Cart should have 2 product",testcart3.getAmountOfProducts() == 2);
        testcart3.addToCart(testproduct3);
        Assert.assertTrue("Cart should have 3 product",testcart3.getAmountOfProducts() == 3);
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
        
        Assert.assertTrue("Shopping cart doesn't add subtotals correctly", flag3);
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
    
}
