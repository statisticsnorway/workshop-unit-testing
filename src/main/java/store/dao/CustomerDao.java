package store.dao;

import store.model.Customer;

public class CustomerDao {


    public boolean exists(String customerName) {
        return false;
    }

    public Customer save(Customer customer) {
        return customer;
    }
}
