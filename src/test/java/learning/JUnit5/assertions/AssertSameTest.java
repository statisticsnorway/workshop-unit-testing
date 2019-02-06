package learning.JUnit5.assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.model.Customer;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("Assertion: assertSame()/assertNotSame() in JUnit 5")
public class AssertSameTest {

    Customer customerA = null;
    Customer customerB = null;

    @BeforeEach
    void setUp() {
        customerA = new Customer();
        customerA.setName("Customer A");
        customerA.setAge(23);
        customerA.setBudget(5000);

        customerB = new Customer();
        customerB.setName("Customer A");
        customerB.setAge(23);
        customerB.setBudget(5000);
    }

    @DisplayName("Verify objects are same using assertSame()")
    @Test
    void verifyObjectsAreSame() {
        Customer customerB = customerA;
        assertSame(customerA, customerB, "Objects should be same");
    }

    @DisplayName("Verify objects are not same using assertNotSame()")
    @Test
    void verifyObjectsAreNotSame() {
        assertNotSame(customerA, customerB, "Objects should be same");
    }
}
