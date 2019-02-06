package learning.JUnit5.assertions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Assertion: assertEquals()/assertNotEquals() in JUnit 5")
public class AssertEqualsTest {
    @DisplayName("Test assertEquals")
    @Test
    void testAssertEquals() {
        Assertions.assertEquals(6, Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum should be equal to 6");
    }

    @DisplayName("Test assertNotEquals")
    @Test
    void testAssertNotEquals() {
        Assertions.assertNotEquals(5, Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> "Sum shouldn't be equal to 5");
    }

    @DisplayName("Test assertEquals with message supplier")
    @Test
    void testAssertEqualsWithMessageSupplier() {
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "Sum is not correct as expected";
        });
        Assertions.assertEquals(6, Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum(), () -> actualResult);
    }
}
