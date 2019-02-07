# Mockito

Most of the classes we come across have dependencies, and oftentimes, methods delegate some work to other methods in
other classes.These classes are our dependencies. When unit testing such methods, if we only use JUnit,
our tests will also depend on those methods as well. We want the unit tests to be independent of all other dependencies.

Say we want to test the method ***```addCustomer ```*** in the ***```CustomerService ```*** class, 
and within this ***```addCustomer```***  method, the save method of the ***``` CustomerDao```***  class is invoked. 

* We don’t want to call the real implementation of the ***```CustomerDao ```*** ***``` save() ```*** method for a few reasons:
     * We only want to test the logic inside the addCustomer() in isolation.
     * We may not yet have implemented it.
     * We don’t want the unit test of the ***``` addCustomer()```***  fail if there is a defect in the ***```save() ```*** method in the CustomerDao.

* So we should somehow mock the behavior of the dependencies. This is where mocking frameworks come into play.
* Mockito is what we use for just this, and in this post, we’ll see how to effectively use Mockito to mock those dependencies.

## What is Mockito?

Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API. 

Mockito doesn’t give you hangover because the tests are very readable and they produce clean verification errors.


### Injecting Mocks With Mockito

We could inject a mock to the class under test instead of the real implementation while we run our tests!

Let’s look at an example of a class under test which has a dependency on ***``` CustomerDao```*** :

``` java
public class CustomerService {
    private CustomerDao customerDao;

    public boolean addCustomer(Customer customer) {
        if (customerDao.exists(customer.getName())) {
            return false;
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

```

The following is the test that mocks the dependency using Mockito:

``` java
class CustomerServiceTest {

    @Mock                          
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        //assertion here
    }

}
```

Let’s look at the role of the annotations in the above example.

 * ***```@Mock```*** will create a mock implementation for the CustomerDao
 * ***``` @InjectMocks```***  will inject the mocks marked with ***```@Mock```*** to this instance when it is created.
 * So **when** or **where** are these instances created?
    * Well, it is done in this line, which resides in the setUp method:
      ***```MockitoAnnotations.initMocks(this); ```*** 

 * So these instances would be created at the start of every test method of this test class.


### Mocking Methods With Mockito

Now we have successfully created and injected the mock, and now we should tell the mock how to behave when certain 
methods are called on it.

The **```when ```**  **```then```* pattern:

   * We do this in each of the test methods. The following line of code tells the Mockito framework that we want 
   the **```save() ```** method of the mock DAO instance to return true when passed in a certain customer instance.

   **```when(dao.save(customer)).thenReturn(true); ```**
   
   * **when** is a static method of the Mockito class, and it returns an **OngoingStubbing<T>**
    (**T** is the return type of the method that we are mocking — in this case, it is **boolean**).
   * So if we just extract that out to get ahold of the stub, it looks like this:
   
   **```OngoingStubbing<Boolean> stub = when(dao.save(customer)); ```**
   
   * The following are some of the methods that we can call on this **stub**:
   
     * **```thenReturn(returnValue) ```**  
     * **```thenThrow(exception) ```**
     * **```thenCallRealMethod() ```**
     * **``` thenAnswer()```**
     
   * Do we really need to pass in an actual customer object to the save method here? 
   No, we could use matchers like the following:
      * **```when(dao.save(any(Customer.class))).thenReturn(true); ```**
   * However, when there are multiple parameters to a method, we cannot mix matchers and actual objects. 
   For example, we cannot do the following: 
   **``` Mockito.when(mapper.map(any(), "test")).thenReturn(new Something());```**
   
   * This would compile without a complaint but would fail during runtime with an error saying: 
    ``` matchers can't be mixed with actual values in the list of arguments to a single 
             method``` .
   * We either have to use matchers for all parameters or should pass in real values or objects.
   
   
   Mock behavior of dependencies using ```Mockito.when ```:
   
   