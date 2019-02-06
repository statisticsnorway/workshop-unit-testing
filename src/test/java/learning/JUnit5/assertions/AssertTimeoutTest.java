package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Execute JUnit5 assertTimeOut")
public class AssertTimeoutTest {
    @DisplayName("Tests assertTimeOut()")
    @Test
    void testAssertTimeout() {
        assertAll("assertTimeout():",
                () -> assertTimeout(ofMinutes(1), () -> {
                    return "result";
                }),
                () -> assertTimeout(Duration.ofMillis(100), () -> {
                    //Thread.sleep(200);
                    System.out.println("assertTimeout()");
                    return "result";
                })
        );
    }

    @DisplayName("Tests assertTimeoutPreemptively()")
    @Test
    void testAssertTimeoutPreemptively() {
        assertAll("assertTimeutPreemptively():",
                () -> assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
                    //Thread.sleep(200);
                    System.out.println("assertTimeoutPreemptively()");
                    return "result";
                })
        );
    }

    @DisplayName("Tests timeoutNotExceededWithResult()")
    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @DisplayName("Tests timeoutNotExceededWithMethod()")
    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        int actualMinutes = assertTimeout(ofMinutes(2), AssertTimeoutTest::getFixedMinutes);
        assertEquals(1, actualMinutes);
    }

    private static Integer getFixedMinutes() throws InterruptedException {
        //Thread.sleep(200);
        return 1;
    }
}
