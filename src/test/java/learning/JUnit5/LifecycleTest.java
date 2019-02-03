package learning.JUnit5;

import org.junit.jupiter.api.*;

import java.util.stream.Stream;

@DisplayName("JUNIT5 test case execution")
class LifecycleTest {
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }

    @DisplayName("Sample test-1")
    @Test
    void testAssertEquals()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals( 6 , Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum should be equal to 6");
    }

    @DisplayName("Sample test-2")
    @Test
    void testAssertNotEquals()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertNotEquals( 5 , Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum shouldn't be equal to 5");
    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}
