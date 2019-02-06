package learning.JUnit5.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.dataset.OnlineStore;
import store.model.Customer;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

public class AssertNotNullTest extends OnlineStore {

    @DisplayName("assertNotNull example")
    @ParameterizedTest
    @MethodSource("learning.JUnit5.annotations.solutions.ParametrizedAnnotationTest#generateCustomerData")
    void verifyCustomerListNotHasNullCustomer(List<Customer> customerList){
        customerList.forEach(customer -> {
            assertNotNull(customer, "Customer name should not be null");
        });
    }
}
