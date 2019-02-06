package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.model.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Execute JUnit5 assertSame()/assertNotSame/() and assertNull()/assertNotNull()")
public class AssertNotNullTest {
    @DisplayName("Tests assertNotNull()/assertNull()")
    @Test
    void testAssertNotNull() {
        String nullString = null;
        String notNullString = "statistics norway IT development";

        assertAll("assertNull() / assertNotNull()",
                () -> assertNotNull(notNullString, "Object should not be null"),
                () -> assertNotNull(nullString, "Object should not be null"),
                () -> assertNull(nullString, "Object should be null"),
                () -> assertNull(notNullString, "Object should not be null"));
    }

    @DisplayName("assertNotNull example")
    @ParameterizedTest
    @MethodSource("learning.JUnit5.annotations.solutions.ParametrizedAnnotationTest#generateCustomerData")
    void verifyCustomerListNotHasNullCustomer(List<Customer> customerList){
        customerList.forEach(customer -> {
            assertNotNull(customer, "Customer name should not be null");
        });
    }

    @DisplayName("Tests AssertSame()/assertNotSame()")
    @Test
    void testAssertNotSame() {
        String originalObject = "statistics norway IT development";
        String cloneObject = originalObject;
        String otherObject = "statistics norway non-IT development";

        assertAll("assertSame() / assertNotSame()",
                () -> assertNotSame(originalObject, otherObject, "Objects should not be same"),
                () -> assertNotSame(originalObject, cloneObject, "Object should not be same"),
                () -> assertSame(originalObject, cloneObject, "Object should be same"),
                () -> assertSame(originalObject, otherObject, "Object should be same"));
    }
}