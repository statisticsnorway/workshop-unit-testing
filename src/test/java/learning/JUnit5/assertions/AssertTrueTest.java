package learning.JUnit5.assertions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.dataset.OnlineStore;
import store.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Assertion: assertTTrue()/assertFalse() in JUnit 5")
public class AssertTrueTest extends OnlineStore {

    private static List<Customer> customerList = null;

    @BeforeAll
    static void setup() {
        Iterable<Customer> customerIterable = mall.getCustomerList();
        customerList = new ArrayList<>();
        Consumer<Customer> consumer = o -> customerList.add(o);
        customerIterable.forEach(consumer);
    }

    @DisplayName("assertTrue example")
    @Test
    void verifyCustomerListIsNotEmpty() {
        assertTrue(customerList.size() > 0, () -> "Customer List should not be empty.");
    }

    @DisplayName("assertFalse example")
    @Test
    void verifyCustomerListConsistStringTypeData() {
        assertFalse(customerList
                .stream()
                .filter(customer -> customer.getAge() < 18)
                .collect(Collectors.toList()).size() > 0, "All customers should be of age greator than 18");
    }
}

