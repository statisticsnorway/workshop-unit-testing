package store.dao;

import store.model.Customer;

public class CustomerDao {


    public boolean exists(int customerId) {
        System.out.println("Inside exists");
        return false;
    }

    public Customer save(Customer customer) {
        System.out.println("Inside save");
        return customer;
    }

    public void updateAge(Customer customer, Integer age) {
        customer.setAge(age);
    }

    public boolean delete(Customer customer) {
        return true;
    }
}
