package learning.lambdas;

import store.dataset.OnlineStore;
import store.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LamdasExamples extends OnlineStore {

    public List<String> filterCustomerListWithNameStartingFromA(){
        List<Customer> customerList = this.mall.getCustomerList();
        List<String> nameList = new ArrayList<>();

        Consumer<Customer> consumer = o -> nameList.add(o.getName());

        List<String> filteredCustomers =
                customerList.stream()
                .map(Customer::getName)
                .filter(cust -> cust.startsWith("A"))
                .collect(Collectors.toList());

        return filteredCustomers;
    }

    public static void main(String[] args) {
        LamdasExamples lamdasSol = new LamdasExamples();
        System.out.println(lamdasSol.filterCustomerListWithNameStartingFromA());
    }
}
