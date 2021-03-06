package learning.JUnit5;

import org.junit.jupiter.api.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("JUnit-5 test cases")
public class LifecycleJUnit5Test {

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed");
    }

    @DisplayName("Sample test-1 \uD83D\uDE31 ")
    @Test
    void testAssertEquals() {
        System.out.println("======TEST ONE EXECUTED=======");
        assertEquals(6, Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum should be equal to 6");
    }

    @Disabled
    @DisplayName("Sample test-2")
    @Test
    void testAssertNotEquals() {
        System.out.println("======TEST TWO EXECUTED=======");
        assertNotEquals(5, Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum should be equal to 6");
    }

    @AfterEach
    void tearThis() {
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear() {
        System.out.println("@AfterAll executed");
    }

}
