## JUnit 5 Assertions

JUnit Jupiter comes with many of the assertion methods that JUnit 4
 has and adds a few that lend themselves well to being used with Java 8 lambdas. 
 
 
Before starting on JUnit 5 asssertions, let's look into how [Java 8 lambas](../../../../main/java/learning/lambdas/java8lambdas.md) works

Assertions are utility methods to support asserting conditions in tests. 
These methods are accessible through the Assert class, in JUnit 4, and the Assertions one,
 in JUnit 5.

JUnit 5 kept many of the assertion methods of JUnit 4 while adding few new ones that take
 advantage of the Java 8 support.

Also in this version of the library, assertions are available for all primitive types,
 Objects, and arrays (either 
of primitives or Objects).

The order of the parameters of the assertions changed, moving the output message
 parameter as the last parameter. 
Thanks to the support of Java 8, the output message can be a Supplier, allowing lazy 
evaluation of it.


## Writing Assertions with JUnit 5

Use ``` org.junit.jupiter.api.Assertions``` class. It provides *static* factory methods
 that we can use for writing assertions.

Before we will take a closer look at these methods, we have to know a few basic rules:
 
  * If we want to specify a custom error message that is shown when our assertion fails, 
  we have to pass this message as the last method parameter of the invoked assertion method.
  
  * If we want to compare two values (or objects), we have to pass these values 
  (or objects) to the invoked assertion 
 method in this order: the expected value (or object) and the actual value (or object).
 
 1. **assertArrayEquals** and **assertArrayNotEquals**
    * The assertArrayEquals assertion verifies that the expected and the actual arrays 
    are equals.
    
    ```
    @Test
    public void whenAssertingArraysEquality_thenEqual() {
        char[] expected = { 'J', 'u', 'p', 'i', 't', 'e', 'r' };
        char[] actual = "Jupiter".toCharArray();
     
        assertArrayEquals(expected, actual, "Arrays should be equal");
    }
    ```
    If the arrays aren’t equal, the message “Arrays should be equal” will be displayed as
     output.
    
 2. **assertEquals and assertNotEquals**
    * In case we want to assert that two floats are equals, we can use the simple
     assertEquals assertion:
    ```
    @Test
    public void whenAssertingEquality_thenEqual() {
        float square = 2 * 2;
        float rectangle = 2 * 2;
     
        assertEquals(square, rectangle);
    }

    ```
    
    **Note**: If we want to assert that the actual value differs by a predefined delta 
    from the expected value, 
    we can still use the assertEquals but we have to pass the delta value as the third 
    parameter:
    
    ```
    @Test
    public void whenAssertingEqualityWithDelta_thenEqual() {
        float square = 2 * 2;
        float rectangle = 3 * 2;
        float delta = 2;
     
        assertEquals(square, rectangle, delta);
    }
    ```
    
  3. **assertTrue** and **assertFalse**
     * With the assertTrue assertion, it’s possible to verify the supplied conditions are
      true:
     
     ```
     @Test
     public void whenAssertingConditions_thenVerified() {
         assertTrue(5 > 4, "5 is greater the 4");
         assertTrue(null == null, "null is equal to null");
     }
     
     ```
     
     Thanks to the support of the lambda expression, it’s possible to supply a 
     BooleanSupplier to the assertion instead of a boolean condition.
     
     Let’s see how we can assert the correctness of a BooleanSupplier using the 
     assertFalse assertion:
     
     ```
     @Test
     public void givenBooleanSupplier_whenAssertingCondition_thenVerified() {
         BooleanSupplier condition = () -> 5 > 6;
      
         assertFalse(condition, "5 is not greater then 6");
     }
     ```
     
   4. **assertNull** and **assertNotNull**
      * When we want to assert that an object is not null we can use the assertNotNull 
      assertion:
      
      ```
      @Test
      public void whenAssertingNotNull_thenTrue() {
          Object obj = new Object();
       
          assertNotNull(obj, () -> "The object should not be null");
      }

      ```
      
      In the opposite way, we can use the assertNull assertion to check if the actual is null:
      
      ```
      @Test
      public void whenAssertingNull_thenTrue() {
          Object obj = null;
       
          assertNull(obj, () -> "The obj should be null");
      }
      
      ```
      **Note**: In both cases, the failure message will be retrieved in a lazy way since 
      it’s a ***Supplier***.
      
   5. **assertSame** and **assertNotSame**
       * When we want to assert that the expected and the actual refer to the same Object, 
       we must use the assertSame assertion:
       
       ```
       @Test
       public void whenAssertingSameObject_thenSuccessfull() {
           String language = "Java";
           Optional<String> optional = Optional.of(language);
        
           assertSame(language, optional.get());
       }
       
       ```
   6. **fail**
       * The fail assertion fails a test with the provided failure message as well as 
       the underlying cause. 
       This can be useful to mark a test when it’s development it’s not completed:
       
       ```
       @Test
       public void whenFailingATest_thenFailed() {
           // Test not completed
           fail("FAIL - test not completed");
       }
       ```
       
         Refer [code examples](assertions)
   
   7. **assertAll**
       * One of the new assertion introduced in JUnit 5 is assertAll.
         
         This assertion allows the creation of grouped assertions, where all the assertions
          are executed and their failures are reported together. In details, this assertion
           accepts a heading, that will be included in the message string for the 
           MultipleFailureError, and a Stream of Executable.
         
         Let’s define a grouped assertion: 
         
        ```
         @Test
         public void givenMultipleAssertion_whenAssertingAll_thenOK() {
             assertAll(
               "heading",
               () -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
               () -> assertEquals("java", "JAVA".toLowerCase()),
               () -> assertEquals(null, null, "null is equal to null")
             );
         }
         ```
       [Code example](assertions/AssertAllTest.java)
       
   8. **assertIterableEquals**
       * The assertIterableEquals asserts that the expected and the actual iterables are 
       deeply equal.
         
         In order to be equal, both iterable must return equal elements in the same order 
         and it isn’t required that the two iterables are of the same type in order to be 
         equal.
         
         With this consideration, let’s see how we can assert that two lists of different 
         types (LinkedList and ArrayList for example) are equal:
         
        ```
         @Test
         public void givenTwoLists_whenAssertingIterables_thenEquals() {
             Iterable<String> al = new ArrayList<>(asList("Java", "Junit", "Test"));
             Iterable<String> ll = new LinkedList<>(asList("Java", "Junit", "Test"));
          
             assertIterableEquals(al, ll);
         }
         ```
   9. **assertThrows**
        * In order to increase simplicity and readability, the new assertThrows assertion 
        allows us a clear and a simple way to assert if an executable throws the specified 
        exception type.
          
          Let’s see how we can assert a thrown exception:
          
         ```
         @Test
             public void testConvertToDoubleThrowException() {
                 String age = "eighteen";
         
                 assertThrows(NumberFormatException.class, () -> { convertToInt(age); });
             }
         
             private static Integer convertToInt(String str) {
                 if (str == null) {
                     return null;
                 }
                 return Integer.valueOf(str);
             }
         ```
         
   * The assertion will fail if no exception is thrown, or if an exception of a 
          different type is thrown.
          
   10. **assertTimeout** and **assertTimeoutPreemptively**
     
        * In case we want to assert that the execution of a supplied Executable ends 
     before a given Timeout, we can use the assertTimeout assertion:
     
         ```
         @Test
              public void whenAssertingTimeout_thenNotExceeded() {
                  assertTimeout(
                    ofSeconds(2), 
                    () -> {
                      // code that requires less then 2 minutes to execute
                      Thread.sleep(1000);
                    }
                  );
              }
         ```
   However, with the ***assertTimeout*** assertion, the supplied executable will be executed in the same thread of the calling code. 
   Consequently, execution of the supplier won’t be preemptively aborted if the 
   timeout is exceeded.
     
   In case we want to be sure that execution of the executable will be aborted once 
     it exceeds the timeout, we can use the ***assertTimeoutPreemptively*** assertion.
     
   Both assertions can accept, instead of an Executable, 
     a ThrowingSupplier, representing any generic block of code that returns an object 
     and that can potentially throw a Throwable.
     
   Let's move to next topic [Dynamic Tests](DynamicTests.md)

          
    
    
    