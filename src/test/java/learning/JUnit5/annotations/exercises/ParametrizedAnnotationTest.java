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
    void shouldGetCustomerList(){
        //hint: fetch values through generateCustomerData. Make changes as per Argument Provider
        // Use : assertTrue((customerList.size() == 10), "Fetched list should contain 10 customer records");
       //TODO: Verify that list of customers generated through sample model contains all the 10 customers
    }

    static List<Customer> generateCustomerData(){
        Iterable<Customer> customerIterable = mall.getCustomerList();
        List<Customer> customerList = new ArrayList<>();
        Consumer<Customer> consumer = o -> customerList.add(o);

        customerIterable.forEach(consumer);
        return customerList;
    }


}
