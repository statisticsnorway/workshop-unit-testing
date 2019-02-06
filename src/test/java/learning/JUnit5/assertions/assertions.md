## JUnit 5 Assertions

JUnit Jupiter comes with many of the assertion methods that JUnit 4
 has and adds a few that lend themselves well to being used with Java 8 lambdas. 
 
 
Before starting on JUnit 5 asssertions, let's look into how [Java 8 lambas](../../../../../main/java/learning/lambdas/java8lambdas.md) works

Assertions are utility methods to support asserting conditions in tests. 
These methods are accessible through the Assert class, in JUnit 4, and the Assertions one, in JUnit 5.

JUnit 5 kept many of the assertion methods of JUnit 4 while adding few new ones that take advantage of the Java 8 support.

Also in this version of the library, assertions are available for all primitive types, Objects, and arrays (either 
of primitives or Objects).

The order of the parameters of the assertions changed, moving the output message parameter as the last parameter. 
Thanks to the support of Java 8, the output message can be a Supplier, allowing lazy evaluation of it.


## Writing Assertions with JUnit 5

Use ``` org.junit.jupiter.api.Assertions``` class. It provides *static* factory methods that we can use for writing assertions.

Before we will take a closer look at these methods, we have to know a few basic rules:
 
  * If we want to specify a custom error message that is shown when our assertion fails, 
  we have to pass this message as the last method parameter of the invoked assertion method.
  
  * If we want to compare two values (or objects), we have to pass these values (or objects) to the invoked assertion 
 method in this order: the expected value (or object) and the actual value (or object).
 
 #### Asserting Boolean values
 
 If we want to verify that a boolean value is true, 
 we have to use the assertTrue() method of the Assertions class. In order words, we have to use this assertion:
 
 ```
     public static void assertTrue(boolean condition) 
 
     public static void assertTrue(boolean condition, Supplier<String> messageSupplier) 
     
     public static void assertTrue(BooleanSupplier booleanSupplier)
     
     public static void assertTrue(BooleanSupplier booleanSupplier, String message) 
 
     public static void assertTrue(boolean condition, String message) 
 
     public static void assertTrue(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier) 
 ```
 **Code** Refer [AssertTrueTest.java](AssertTrueTest.java)
 
 #### Asserting Null or Not Null
 
 If we want to verify that an object is not null, we have to use the assertNotNull() 
 method of the Assertions class. 
 
```
public static void assertNotNull(Object actual)

public static void assertNotNull(Object actual, String message) 

public static void assertNotNull(Object actual, Supplier<String> messageSupplier) 
```

**Code** [AssertNotNullTest.java](AssertNotNullTest.java)

#### Asserting two objects are equal

If we want to verify that the expected value (or object) is equal to the actual value (or object), 
we have to use the assertEquals() method of the Assertions class.

```
    public static void assertEquals(int expected, int actual) 

    public static void assertEquals(int expected, int actual, String message) 

    public static void assertEquals(int expected, int actual, Supplier<String> messageSupplier) 

```

It also has overloaded methods for different data types e.g. boolean[], char[], int[] etc.

If we want to verify that the expected value (or object) is not equal to the actual value (or object), we have to use
 the **```assertNotEquals() ```** method of the Assertions class.


**Code** [AssertEqualsTest.java](AssertEqualsTest.java)

#### Asserting Object References

To ensure that two objects refer to the same object, use the ```assertSame() ``` method of the Assertions class.

**``` @assertSame(Object expected, Object actual, String message)```**

### Asserting two arrays are equal
To verify that two arrays are equal, use the ```assertArrayEquals() ``` method of the Assertions class. For example, 
if we want verify that two int arrays are equal, we have to use this assertion.

**``` @assertArrayEquals(int[] expected, int[] actual, Supplier<String> messageSupplier)```**

