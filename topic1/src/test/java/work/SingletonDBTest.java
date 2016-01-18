package work;

import org.junit.*;
import org.hamcrest.*;

public class SingletonDBTest 
{
    @Test
    public void singletonCreatesSomeInstance()
    {
        SingletonDBConnection testSingle1 = null;
        testSingle1 = SingletonDBConnection.getInstance();
        Assert.assertNotNull("Shouldn't be NULL", testSingle1);
    }
    @Test
    public void singletonCreatesOnlyOneInstance()
    {
        SingletonDBConnection testSingle2 = null;
        SingletonDBConnection testSingle3 = null;
        testSingle2 = SingletonDBConnection.getInstance();
        testSingle3 = SingletonDBConnection.getInstance();
        Assert.assertNotNull("Shouldn't be NULL", testSingle2);
        Assert.assertNotNull("Shouldn't be NULL", testSingle3);
        Assert.assertSame("Should be the same object", testSingle2, testSingle3);
    }
    /*
        I Tried to make a test to prove that a SingletonDBConnection instance 
        can not be created by calling the constructor directly, because it's 
        private. The problem is that the class won't compile if it tries to 
        call the constructor SingletonDBConnection(), so I skipped the test
    */
    @Ignore("Couldn't figure out how to make it work yet")
    @Test
    public void singletonCreatesObjectOnlyThrough_getInstance()
    {
        SingletonDBConnection testSingle4 = null;
//        testSingle4 = new SingletonDBConnection();
        Assert.assertNull("Should be NULL", testSingle4);
    }
}
