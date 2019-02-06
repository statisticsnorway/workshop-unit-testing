package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Execute JUnit5 assertArrayEquals()")
public class AssertArrayEqualsTest {
    @DisplayName("Test assertArrayEquals")
    @Test
    void testAssertArrayEquals() {
        assertAll("Compare arrays",
                () -> assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3},
                        () -> "Both arrays should be equal"),
                () -> assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 3, 2},
                        () -> "Both arrays should be equal in ordering"),
                () -> assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4},
                        () -> "Both arrays should be equal in content"));
    }
}
