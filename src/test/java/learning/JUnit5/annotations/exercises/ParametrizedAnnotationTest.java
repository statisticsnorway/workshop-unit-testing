package learning.JUnit5.annotations.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import store.dataset.OnlineStore;
import store.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Parameterized test JUnit-5")
public class ParametrizedAnnotationTest extends OnlineStore {

    @DisplayName("Test to verify the generated customer list")
    @ParameterizedTest
    void shouldGetCustomerList(List<Customer> customerList){
       //TODO: Verify that list of customers generated through sample model contains all the 10 customers
    }

}
