package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import store.dataset.OnlineStore;

import java.util.stream.Stream;

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
        assertEquals(expStatus, status);
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

    @DisplayName("Pass params using CsvSource")
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void shouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        assertEquals(expected, actualValue);
    }

    @DisplayName("Should pass the method parameters provided by the test-data.csv file")
    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @CsvFileSource(resources = "/TestData.csv", numLinesToSkip = 1)
    void shouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @DisplayName("Should pass the method parameters provided by factory method")
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void shouldReturnTrueForBlankStrings(String input, boolean expected) {
        assertEquals(expected, input.isEmpty());
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("not blank", false)
        );
    }

    @DisplayName("Should pass the method parameters provided by Argument Provider")
    @ParameterizedTest
    @ArgumentsSource(CustomArgumentProvider.class)
    void shouldReturnTrueForBlankStringsWithCustomProvider(String input, boolean expected) {
        assertEquals(expected, input.isEmpty());
    }
}
