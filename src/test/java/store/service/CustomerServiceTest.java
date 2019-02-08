package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import store.dao.CustomerDao;
import store.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private Customer testCustomer = null;

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @Captor
    private ArgumentCaptor<Customer> customerArgument;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testCustomer = new Customer();
        testCustomer.setAge(23);
        testCustomer.setBudget(5000);
        testCustomer.setName("Test Customer A");
        testCustomer.setId(1234);
    }

    @Test
    void testAddCustomerReturnsNewCustomer() {
        when(daoMock.save(any(Customer.class))).thenReturn(new Customer());

        Customer customer = new Customer();

        assertNotNull(service.addCustomer(customer), "Added customer should not be null");
    }

    // Using Answer to set an id to the customer which is passed in as a parameter to the mock method.
    @Test
    void testAddCustomerReturnsNewCustomerWithId() {
        when(daoMock.save(any(Customer.class))).thenAnswer(new Answer<Customer>() {
            @Override
            public Customer answer(InvocationOnMock invocation) throws Throwable {

                Object[] arguments = invocation.getArguments();

                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
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

    @DisplayName("Throwing an exception from the mocked method")
    @Test
    void testAddCustomerThrowsException() {
        when(daoMock.save(any(Customer.class))).thenThrow(RuntimeException.class);

        Customer customer = new Customer();

        assertThrows(RuntimeException.class, () -> service.addCustomer(customer));
    }

    @DisplayName("Mocking void methods")
    @Test
    public void testUpdate() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();

                if (arguments != null && arguments.length > 1 && arguments[0] != null
                        && arguments[1] != null) {
                    Customer customer = (Customer) arguments[0];
                    int age = (int) arguments[1];
                    customer.setAge(age);

                }
                return null;
            }
        }).when(daoMock).updateAge(any(Customer.class), any(Integer.class));


        Customer customer = service.changeAge(testCustomer, 25);

        assertNotNull(customer, () -> "Updated customer object shouldn't be null");
    }


    @DisplayName("Verify verification methods")
    @Test
    void testVerificationMethods(){
        when(daoMock.save(any(Customer.class))).thenReturn(testCustomer);

        Customer customer=new Customer();
        assertTrue(service.addCustomer(customer) != null , "Created customer shouldn1 be null");

        //verify that the save method has been invoked
        verify(daoMock).save(any(Customer.class));

        //verify that the exists method is invoked one time
        verify(daoMock, times(1)).exists(anyInt());

        //verify that the delete method has never been  invoked
        verify(daoMock, never()).delete(any(Customer.class));
    }

    @DisplayName("Verify argument capture")
    @Test
    void testArgumentCapturing(){
        service.register(new Customer());

        verify(daoMock).save(customerArgument.capture());

        assertNotNull(customerArgument.getValue().getToken(), "Registration token must not be null");
    }

    @Test
    public void whenSpyingOnList_thenCorrect() {
        List<String> list = new ArrayList<String>();
        List<String> spyList = Mockito.spy(list);

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

}