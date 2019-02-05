package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import store.dataset.OnlineStore;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Parameterized test JUnit-5")
public class ParametrizedAnnotationTest extends OnlineStore {

    enum Status {
        APPROVED,
        REJECT,
        WAITING,
        SUBMITTED,
        COMPLETE;
    }

    @DisplayName("Pass enum values to our test method")
    @ParameterizedTest(name = "{index} => status=''{0}''")
    @EnumSource(Status.class)
    void shouldPassEnumValuesAsMethodParameter(Status status) {
        assertFalse(status.toString().equalsIgnoreCase("PENDING"));
    }

    @DisplayName("Should pass only the specified enum value as a method parameter")
    @ParameterizedTest(name = "{index} => status=''{0}''")
    @EnumSource(value = Status.class, names = {"APPROVED"})
    void shouldPassEnumValueAsMethodParameter(Status status) {
        Status expStatus = Status.APPROVED;
        assertEquals(expStatus,status);
    }

    @DisplayName("Should pass a string of size equal to 5 to our test method")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World", "example"})
    void shouldVerifyStringOfSizeAsMethodParameter(String param) {
        assertTrue(param.length() <= 5, "Parameter should be of length smaller or equal to 5");
    }

    @DisplayName("Pass the method parameters provided by the @ValueSource annotation")
    @ParameterizedTest(name = "{index} => message=''{0}''")
    @ValueSource(strings = {"Hello", "World", "example"})
    void shouldVerifyStringOfSizeAsMethodParameterWIthInvocationName(String param) {
        assertTrue(param.length() <= 5, "Parameter should be of length smaller or equal to 5");
    }



    /*@DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("sumProvider")
    void sum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5)
        );
    }*/
}
