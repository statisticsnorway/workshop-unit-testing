package learning.JUnit5.annotations.solutions;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Parameterized test JUnit-5")
public class ParametrizedAnnotationTest extends OnlineStore {

    @ParameterizedTest
    @MethodSource("generateCustomerData")
    void shouldGetCustomerList(List<Customer> customerList){
        assertTrue((customerList.size() == 10), "Fetched list should contain 10 customer records");
    }

    static Stream<Arguments> generateCustomerData(){
        Iterable<Customer> customerIterable = mall.getCustomerList();
        List<String> nameList = new ArrayList<>();
        Consumer<Customer> consumer = o -> nameList.add(o.getName());

        customerIterable.forEach(consumer);
        Stream<Arguments> of = Stream.of(Arguments.of(nameList));
        return of;
    }

}
