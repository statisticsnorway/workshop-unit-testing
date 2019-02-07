package store.service;

import store.dao.CustomerDao;
import store.model.Customer;

import java.util.UUID;

public class CustomerService {
    private CustomerDao customerDao;

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer addCustomer(Customer customer) {
        if (customerDao.exists(customer.getId())) {
            return null;
        }
        return customerDao.save(customer);
    }

    public Customer changeAge(Customer customer, int age){
        customerDao.updateAge(customer, age);
        return customer;
    }

    public Customer register(Customer customer){
        String uuid = UUID.randomUUID().toString();
        customer.setToken("uuid"+uuid);

        customerDao.save(customer);

        return customer;
    }

}
