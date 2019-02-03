package learning.JUnit4;

import org.junit.*;

public class LifecycleTest {
    @BeforeClass
    public static void setup(){
        System.out.println("@BeforeClass executed");
    }

    @Before
    public void setupThis(){
        System.out.println("@Before executed");
    }

    @Test
    public void testAssertEquals()
    {
        System.out.println("======TEST ONE EXECUTED using JUNIT 4=======");
        Assert.assertEquals("Sum should be equal to 6",6, (3+3));

    }

    @After
    public void tearThis(){
        System.out.println("@After executed");
    }

    @AfterClass
    public static void tear(){
        System.out.println("@AfterClass executed");
    }
}


