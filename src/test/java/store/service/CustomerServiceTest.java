package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import store.dao.CustomerDao;
import store.model.Customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCustomerReturnsNewCustomer(){
        when(daoMock.save(any(Customer.class))).thenReturn(new Customer());

        Customer customer = new Customer();
        assertNotNull(service.addCustomer(customer), "Added customer should not be null");
    }

    // Using Answer to set an id to the customer which is passed in as a parameter to the mock method.
    @Test
    void testAddCustomerReturnsNewCustomerWithId(){
        when(daoMock.save(any(Customer.class))).thenAnswer(new Answer<Customer>() {
            @Override
            public Customer answer(InvocationOnMock invocation) throws Throwable {

                Object[] arguments = invocation.getArguments();

                if(arguments != null && arguments.length>0 && arguments[0] != null){
                    Customer customer = (Customer) arguments[0];
                    customer.setId(1);

                    return customer;
                }
                return null;
            }
        });

        Customer customer = new Customer();

        assertNotNull(service.addCustomer(customer).getId(), "New customer should have Id after successful save operation");
    }

    @Test
    void testAddCustomerThrowsException(){
        when(daoMock.save(any(Customer.class))).thenThrow(RuntimeException.class);

        Customer customer = new Customer();

        assertThrows(RuntimeException.class, ()-> service.addCustomer(customer));
    }



}