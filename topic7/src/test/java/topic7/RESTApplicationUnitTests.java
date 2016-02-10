package topic7;

import org.junit.*;
import java.util.List;
import java.util.LinkedList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@EnableMongoRepositories
@WebAppConfiguration
public class RESTApplicationUnitTests {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRestController userRestController;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductRestController productRestController;
    
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;
    private static User user5;
    private static User user6;
    private static User user7;
    private static User user8;
    
    private static Product product1;
    private static Product product2;
    private static Product product3;
    private static Product product4;
    private static Product product5;
    private static Product product6;
    private static Product product7;
    private static Product product8;
    private static Product product9;
    private static Product product10;
    private static Product product11;
    private static Product product12;
    private static Product product13;
    private static Product product14;
    private static Product product15;
    private static Product product16;
    
    @Before
    public void contextLoads() {
        user1 = userRepository.save(new User("jhoeller", "pass1", "John1", "Smith1"));
        user2 = userRepository.save(new User("dsyer", "pass2", "John2", "Smith2"));
        user3 = userRepository.save(new User("pwebb", "pass3", "John3", "Smith3"));
        user4 = userRepository.save(new User("ogierke", "pass4", "John4", "Smith4"));
        user5 = userRepository.save(new User("rwinch", "pass5", "John5", "Smith5"));
        user6 = userRepository.save(new User("mfisher", "pass6", "John6", "Smith6"));
        user7 = userRepository.save(new User("mpollac", "pass7", "John7", "Smith7"));
        user8 = userRepository.save(new User("jlong", "pass8", "John8", "Smith8"));

        product1 = productRepository.save(new Product("car1", 10000, "transport", "cheap car"));
        product2 = productRepository.save(new Product("car2", 40100, "transport", "cheap car"));
        product3 = productRepository.save(new Product("car3", 26000, "transport", "cheap car"));
        product4 = productRepository.save(new Product("car4", 10030, "transport", "cheap car"));
        product5 = productRepository.save(new Product("car5", 90312, "transport", "cheap car"));
        product6 = productRepository.save(new Product("car6", 11000, "transport", "cheap car"));
        product7 = productRepository.save(new Product("car7", 10012, "transport", "cheap car"));
        product8 = productRepository.save(new Product("car8", 20100, "transport", "cheap car"));
        product9 = productRepository.save(new Product("car9", 15500, "transport", "cheap car"));
        product10 = productRepository.save(new Product("bike1", 144, "transport", "cheap bike"));
        product11 = productRepository.save(new Product("shoe1", 10000, "clothing", "cheap shoe"));
        product12 = productRepository.save(new Product("shoe2", 10000, "clothing", "cheap shoe"));
        product13 = productRepository.save(new Product("shoe3", 10000, "clothing", "cheap shoe"));
        product14 = productRepository.save(new Product("shoe4", 10000, "clothing", "cheap shoe"));
        product15 = productRepository.save(new Product("shoe5", 10000, "clothing", "cheap shoe"));
        product16 = productRepository.save(new Product("shoe6", 10000, "clothing", "cheap shoe"));


        
    }
    
    @Test
    public void addUserMethodUnitTest() {
        //Adds an user and asserts if it was successfully added.
        User user9 = new User("Jlencinas", "joa", "Joa", "Lencinas");
        userRestController.addUser(user9);
        User user10 = userRepository.findOne("Jlencinas");
        Assert.assertNotNull("\n\nFailed, controller didn't add the user\n\n", user10);
        boolean flag = user9.compareTo(user10) == 0;
        Assert.assertTrue("\n\nFailed, controller didn't add the user\n\n", flag);
    }
    
    @Test
    public void deleteUserWithoutLoggingInUnitTest() {
        //Method doesn't delete a logged out user
        User user9 = userRepository.findOne("jlong");
        userRestController.deleteUser("jlong");
        User user10 = userRepository.findOne("jlong");
        Assert.assertNotNull("\n\nController deleted the user without logging in\n\n", user10);
    }
    
    @Test
    public void deleteUserUnitTest() {
        //Checks if method deletes correctly the specified user
        User user9 = userRepository.findOne("jlong");
        userRestController.loginUser("jlong", "pass8");
        userRestController.deleteUser("jlong");
        User user10 = userRepository.findOne("jlong");
        Assert.assertNull("\n\nController didn't delete the user\n\n", user10);
    }
    
    
    @Test
    public void updateMethodUnitTest() {
        //Retrieves some user, modifies 2 fields, and Updates it.
        userRepository.save(new User("joalencinas", "joa", "Joa", "Lencinas"));
        userRestController.loginUser("joalencinas", "joa");
        User user9 = userRepository.findOne("joalencinas");
        user9.setFirstName("Joaquin");
        user9.setLastName("");
        userRestController.updateUser(user9);
        User user10 = userRepository.findOne("joalencinas");
        boolean flag = user9.compareTo(user10) == 0;
        Assert.assertTrue("\n\nMethod didn't update user correctly\n\n", flag);
        userRestController.logoutUser(user10);
    }
    
    @Test
    public void updateUserWithoutLoggingIn() {
        //Retrieves some user, modifies 2 fields, and Updates it.
        userRestController.addUser(new User("joalencinas", "joa", "Joa", "Lencinas"));
        User user9 = userRepository.findOne("joalencinas");
        user9.setFirstName("Joaquin");
        user9.setLastName("");
        userRestController.updateUser(user9);
        User user10 = userRepository.findOne("joalencinas");
        boolean flag = user9.compareTo(user10) == 0;
        Assert.assertTrue("\n\nMethod didn't update user correctly\n\n", flag);
    }
    
    @Test
    public void readAllMethodUnitTest() {
        //Calls to findAllUers method, asserts if the result is correct.
        List<User> usersRead = userRestController.readAllUsers();
        Assert.assertNotNull("\n\nReading All users returned null\n\n", usersRead);
        String[] usernames = {"jhoeller" , "dsyer" , "pwebb" , "ogierke" , "rwinch" ,
             "mfisher" , "mpollac"};
        User aux = null;
        boolean flag = usersRead.size() > 0;
        boolean flag2 = false;
        for (int i = 0; i<usernames.length; i++) {
            aux = userRepository.findOne(usernames[i]);
            if (aux != null) {
                for (int j = 0; j<usersRead.size(); j++) {
                    flag2 = flag2 || userRepository.findOne(usernames[i]).compareTo(usersRead.get(j)) == 0;
                }
                flag = flag && flag2;
                flag2 = false;
            } else {
                flag = false;
                break;
            }
        }
        Assert.assertTrue("\n\nSome user is missing in readAll return value", flag);
        
    }
    
    @Test
    public void readUserWithoutLoggingIn() {
        User user10 = userRestController.readUserByUsername("joalencinas");
        Assert.assertNull("\n\nController read user despite of being logged out\n\n", user10);
    }
    
    @Test
    public void readUserLoggingInFirst() {
        userRestController.loginUser("joalencinas", "joa");
        User user10 = userRestController.readUserByUsername("joalencinas");
        User user11 = userRepository.findOne("joalencinas");
        boolean flag = user10.compareTo(user11) == 0;
        Assert.assertTrue("\n\nReading user failed\n\n", flag);
        userRestController.logoutUser(user11);
    }
    
    @Test
    public void addProductToCartWithoutLoggingIn() {
        
        userRestController.addProductToCart("joalencinas", product1);
        User user10 = userRepository.findOne("joalencinas");
        boolean flag = user10.getShoppingCart().contains(product1);
        Assert.assertFalse("\n\nController added the product despite of being logged out\n\n", flag);
        
    }
    
    @Test
    public void addProductToCartLoggingInFirst() {
        userRestController.loginUser("joalencinas", "joa");
        userRestController.addProductToCart("joalencinas", product1);
        User user10 = userRepository.findOne("joalencinas");
        List<Product> list = user10.getShoppingCart();
        boolean flag = false;
        for (int i = 0; i<list.size(); i++) {
            flag = flag || list.get(i).compareTo(product1) == 0;
        }
        Assert.assertTrue("\n\nController didn't add the product\n\n", flag);
        userRestController.logoutUser(user10);
    }
    
    @Test
    public void getAllProductsFromGivenUserWhenNotLoggedIn() {
        userRestController.logoutUser(userRepository.findOne("joalencinas"));
        List<Product> list = userRestController.getAllProductsFromUserCart("joalencinas");
        Assert.assertNull("\n\nController returned list of products despite of being logged out\n\n", list);
    }
    
    @Test
    public void getAllProductsFromUserCartUnitTest() {
        userRestController.loginUser("joalencinas", "joa");
        List<Product> list = userRestController.getAllProductsFromUserCart("joalencinas");
        List<Product> list2 = new LinkedList();
        list2.add(product1);
        list.sort(null);
        list2.sort(null);
        boolean flag = list.size() == list2.size();
        if (flag) {
            for (int i = 0; i < list.size(); i++) {
                flag = flag && list.get(i).compareTo(list2.get(i)) == 0;
            }
        }
        Assert.assertTrue("\n\nController didn't return the user's list of products\n\n", flag);
        User user10 = userRepository.findOne("joalencinas");
        userRestController.logoutUser(user10);
    }
    
    @Test
    public void clearCartWithoutLoggingIn() {
        userRestController.clearUserCart("joalencinas");
        List<Product> list = userRepository.findOne("joalencinas").getShoppingCart();
        Assert.assertNotNull("\n\nUser cart should not be empty\n\n", list);
    }
    
    @Test
    public void clearUserCartUnitTest() {
        userRestController.loginUser("joalencinas", "joa");
        userRestController.clearUserCart("joalencinas");
        List<Product> list = userRepository.findOne("joalencinas").getShoppingCart();
        Assert.assertTrue("\n\nUser cart should be empty\n\n", list.size() == 0);
        userRestController.addProductToCart("joalencinas", product1);
        userRestController.addProductToCart("joalencinas", product7);
        userRestController.addProductToCart("joalencinas", product16);
        
    }
    
    @Test
    public void addProductUnitTest() {
        Product product17 = new Product("bike2", 10000, "transport", "cheap bike, right?");
        Product product18 = productRestController.addProduct(product17);
        boolean flag = productRepository.exists(product17.getName());
        Assert.assertTrue("\n\nProduct wasn't added correctly to repository\n\n", flag);
    }
    
    @Test
    public void getAllProductsUnitTest() {
        List<Product> list = productRestController.getAllProducts();
        String[] names = {"car1", "car2", "car3", "car4", "car5", "car6", "car7", 
                    "car8", "car9", "bike1", "shoe1", "shoe2", "shoe3",
                    "shoe4", "shoe5", "shoe6"};
        Assert.assertNotNull("\n\nProducts list returned is null\n\n", list);
        boolean flag = list.size() > 0;
        boolean flag2 = false;
        for (int i = 0; i<names.length; i++) {
            for (int j = 0; j<list.size(); j++) {
                flag2 = flag2 || productRepository.findOne(names[i]).compareTo(list.get(j)) == 0;
            }
            flag = flag && flag2;
            flag2 = false;
        }
        Assert.assertTrue("\n\nSome product is missing on the getAllProducts return list\n\n", flag);
    }
    
    @Test
    public void getProductByNameUnitTest() {
        //The static field Product product4 is the one with the name "car4"
        Product product = productRestController.getProductByName("car4");
        Assert.assertNotNull("\n\nThe product returned by the method getProductByName " + 
            "is null\n\n", product);
        boolean flag = product.compareTo(product4) == 0;
        Assert.assertTrue("\n\nThe product returned by the method getProductByName" + 
            " is different than expected\n\n", flag);
    }
    
    @Test
    public void getProductsByCategoryUnitTest() {
        List<Product> list = productRestController.getProductsByCategory("transport");
        String[] names = {"car1", "car2", "car3", "car4", "car5", "car6", "car7", 
                    "car8", "car9", "bike1"};
        Assert.assertNotNull("\n\nList returned is null\n\n", list);
        boolean flag = list.size() > 0;
        boolean flag2 = false;
        for (int i = 0; i<names.length; i++) {
            for (int j = 0; j<list.size(); j++) {
                flag2 = flag2 || productRepository.findOne(names[i]).compareTo(list.get(j)) == 0;
            }
            flag = flag && flag2;
            flag2 = false;
        }
        Assert.assertTrue("\n\nSome product is missing on the getProductsByCategory" +
            " return list\n\n", flag);
        
    }
    
    @Test
    public void updateProductUnitTest() {
        Product product = productRepository.findOne("bike2");
        product.setPrice(20);
        product.setCategory("Environment-friendly transport");
        product.setDescription("cheap and healthy transport");
        Product product2 = productRestController.updateProduct(product);
        boolean flag = product.compareTo(product2) == 0;
        flag = flag && product2.compareTo(productRepository.findOne("bike2")) == 0;
        Assert.assertTrue("\n\nController didn't update product correctly\n\n", flag);
        
    }
    
    @Test
    public void deleteProductUnitTest() {
        Product product = new Product("bike3", 35, "Environment-friendly transport", "Cheap and Cool");
        productRepository.save(product);
        boolean flag = productRepository.exists("bike3");
        productRestController.deleteProduct(product);
        flag = flag && !productRepository.exists("bike3");
        Assert.assertTrue("\n\nController didn't delete the product correctly\n\n", flag);
        
    }
    
    
    
}

