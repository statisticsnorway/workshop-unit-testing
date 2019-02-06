package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AssertThrowsTest {
    @Test
    void whenAssertingException_thenThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("Exception message");
                }
        );
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    public void testConvertToDoubleThrowException() {
        String age = "eighteen";
        assertThrows(NumberFormatException.class, () -> {
            convertToInt(age);
        });
    }

    private static Integer convertToInt(String str) {
        if (str == null) {
            return null;
        }
        return Integer.valueOf(str);
    }
}
