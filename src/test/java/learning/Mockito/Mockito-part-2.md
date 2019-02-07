# Mockito void methods with Mockito

1. **```doAnswer```**: If we want our mocked void method to do something (mock the behavior despite being void).
2. **```doThrow```**: Then there is **```Mockito.doThrow() ```** if you want to throw an exception from the mocked void method.

   ``` java
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
            assertEquals(25, customer.getAge(), "Age should be updated successfully");
        }
   
   ```
Refer [Code](../../store/service/CustomerServiceTest.java)

## The Two Ways of Testing Void Methods With Mockito

*Methods with return values can be tested by asserting the returned value, but how do we test void methods?*

The void method that you want to test could either be calling other methods to get things done, processing the input parameters,
or maybe generating some values or all of it. 

With Mockito, you can test all of the above scenarios.


### Verify With Mockito

we can verify that certain methods have been called on those mock objects during test execution — 
in addition to or in place of assertions — when the method under test is void.

 * There are two overloaded verify methods.
    * One that accepts only the mock object — we can use this if the method is supposed to be invoked only once.
    * The other accepts the mock and a ```VerificationMode ``` — 
    there are quite a few methods in the Mockito class that provide some useful verificationModes:
        * ``` times(int wantedNumberOfInvocations) ```
        * ``` atLeast( int wantedNumberOfInvocations )```
        * ``` atMost( int wantedNumberOfInvocations )```
        * ``` calls( int wantedNumberOfInvocations ) ```
        * ``` only( int wantedNumberOfInvocations ) ```
        * ``` atLeastOnce()```
        * ``` never()```
        
 ``` java
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
 ```
 
 ### Capture Arguments
 
 Another cool feature is the **```ArgumentCaptor ```**, which allows us to capture any arguments passed into the mocked
  or spied methods.
  
  ``` java
      @Captor
      private ArgumentCaptor<Customer> customerArgument;
  
      @DisplayName("Verify argument capture")
      @Test
      void testArgumentCapturing(){
          service.register(new Customer());
  
          verify(daoMock).save(customerArgument.capture());
  
          assertNotNull(customerArgument.getValue().getToken(), "Registration token must not be null");
      }
  
  ```
  
  ## I Spy: Spying With Mockito
  
  Simply put, the API is **```Mockito.spy() ```**  – to spy on a real object.
  
  This will allow us to call all the normal methods of the object while still tracking every interaction, just as you would with a mock.
  
  ``` java
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
  ```
  
  Note how the **real method add() is actually called** and how the size of spyList becomes 2.
  
  ## The @Spy Annotation
Next – let’s see how to use the ***@Spy*** annotation. We can use ***@Spy*** annotation instead of spy() as in the following example:

``` java
@Spy
List<String> spyList = new ArrayList<String>();
 
@Test
public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
    spyList.add("one");
    spyList.add("two");
 
    Mockito.verify(spyList).add("one");
    Mockito.verify(spyList).add("two");
 
    assertEquals(2, spyList.size());
}

```
 
 ## Stubbing a Spy
 Now – Let’s see how to stub a ***Spy***. We can configure/override the behavior of a method using the same syntax we would use with a mock.
 
 In the following example – we use ***doReturn()*** to override the ***size()*** method:
        
 ``` java
 @Test
 public void whenStubASpy_thenStubbed() {
     List<String> list = new ArrayList<String>();
     List<String> spyList = Mockito.spy(list);
  
     assertEquals(0, spyList.size());
  
     Mockito.doReturn(100).when(spyList).size();
     assertEquals(100, spyList.size());
 }
 
 ```
## Mock vs. Spy in Mockito

   * When Mockito creates a mock – it does so from the Class of an Type, not from an actual instance. 
   * The mock simply creates a **bare-bones shell instance** of the Class, entirely instrumented to track interactions with it.
   * On the other hand, **the spy will wrap an existing instance**. It will still behave in the same way as the normal instance – the only difference is that it will also be instrumented to track all the interactions with it.
   
   In the following example – we create a mock of the ArrayList class:
   
   ``` java
   @Test
   public void whenCreateMock_thenCreated() {
       List mockedList = Mockito.mock(ArrayList.class);
    
       mockedList.add("one");
       Mockito.verify(mockedList).add("one");
    
       assertEquals(0, mockedList.size());
   }

   ```
   
   As you can see – adding an element into the mocked list doesn’t actually add anything – it just calls the method with no other side-effect.
   
   A spy on the other hand will behave differently – it will actually call the real implementation of the add method and add the element to the underlying list:
   
   ``` java
   @Test
   public void whenCreateSpy_thenCreate() {
       List spyList = Mockito.spy(new ArrayList());
    
       spyList.add("one");
       Mockito.verify(spyList).add("one");
    
       assertEquals(1, spyList.size());
   }
   ```