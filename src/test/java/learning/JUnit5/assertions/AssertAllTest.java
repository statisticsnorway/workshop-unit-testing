package learning.JUnit5.assertions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Execute JUnit5 assertAll()")
public class AssertAllTest {
    int[] numbers = {};

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
        int[] numbers = {};
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed");
        numbers = new int[]{0, 1, 2, 3, 4};
    }

    @DisplayName("Verify assertAll without heading parameter")
    @Test
    void groupAssertionsDemo1() {
        assertAll(() -> assertEquals(0, numbers[0]),
                () -> assertEquals(3, numbers[3]),
                () -> assertEquals(1, numbers[1]),
                () -> assertEquals(4, numbers[4])
        );
    }

    @DisplayName("Verify assertAll with heading parameter")
    @Test
    void groupAssertionsDemo2() {
        assertAll("Match numbers",
                () -> assertEquals(0, numbers[0]),
                () -> assertEquals(3, numbers[3]),
                () -> assertEquals(1, numbers[1]),
                () -> assertEquals(4, numbers[4])
        );
    }

    @DisplayName("Verify assertAll with collection of executables")
    @Test
    void groupAssertionsDemo3() {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        List<String> toList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

        AtomicInteger i = new AtomicInteger();

        assertAll(myList.stream().map(key -> (Executable) () -> {
            assertNotNull(key);
            assertEquals(toList.get(i.get()), key);
            i.getAndIncrement();
        }));
    }

    /*@DisplayName("Possible mistakes while using multiple assertions")
    @Test
    void groupAssertionsDemo4() {
        assertAll("numbers",
                () -> {
                    assertEquals(0, numbers[0]);
                    assertEquals(3, numbers[3]);
                    assertEquals(2, numbers[1]);
                    assertEquals(3, numbers[4]);
                }
        );
    }*/

    @DisplayName("assertEquals Vs assertAll")
    @Test
    void groupAssertionsDemo5() {
        assertEquals(0, numbers[0], "Value at 0th position should be 0");
        assertEquals(3, numbers[3], "Value at 3rd position should be 3");
        //assertEquals(2, numbers[1], "Value at 1st position should be 1");
        //assertEquals(3, numbers[4], "Value at 4th position should be 4");
    }

    @DisplayName("Dependent assertions")
    @Test
    void dependentAssertionsDemo() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
                () -> {
                    String firstName = "firstName";
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("f")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    String lastName = "lastName";
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("l")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

}
