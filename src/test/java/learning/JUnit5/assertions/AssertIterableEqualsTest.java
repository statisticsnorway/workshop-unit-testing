package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DisplayName("Execute JUnit5 assertIterableEquals()")
public class AssertIterableEqualsTest {
    @DisplayName("Test assertIterableEquals")
    @Test
    void testAssertIterableEquals() {
        Iterable<Integer> listOne = new ArrayList<>(Arrays.asList(1,2,3,4));
        Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1,2,3,4));
        Iterable<Integer> listThree = new ArrayList<>(Arrays.asList(1,2,3));
        Iterable<Integer> listFour = new ArrayList<>(Arrays.asList(1,2,4,3));

        assertAll("Compare iterables",
                () -> assertIterableEquals(listOne, listTwo,
                        () -> "Both lists should be equal"),
                () -> assertIterableEquals(listOne, listThree,
                        () -> "Both lists should be equal in content"),
                () -> assertIterableEquals(listOne, listFour,
                        () -> "Both arrays should be equal in ordering"));
    }
}
