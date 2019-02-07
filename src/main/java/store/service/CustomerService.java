package store.service;

import store.dao.CustomerDao;
import store.model.Customer;

public class CustomerService {
    private CustomerDao customerDao;

    public Customer addCustomer(Customer customer) {
        if (customerDao.exists(customer.getName())) {
            return customer;
        }
        return customerDao.save(customer);
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
