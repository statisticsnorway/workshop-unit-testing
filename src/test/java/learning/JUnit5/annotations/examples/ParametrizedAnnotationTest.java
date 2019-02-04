package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Parameterized test JUnit-5")
public class ParametrizedAnnotationTest {

    @BeforeEach
    //void setUpCustomerList()

    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @ValueSource(strings = { "Hello", "JUnit" })
    void withValueSource(String word) {
        System.out.println(word);
    }
/*
    @ParameterizedTest(name = "Run#{index} with [{arguments}]")
    @ValueSource(strings = {"Joe", "Steven", "Patrick", "Diana", "Chris", "Kathy", "Alice", "Andrew", "Martin", "Amy"})*/

}
