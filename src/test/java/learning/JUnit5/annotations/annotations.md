# @JUnit 5 Annotations

In this section we will see the basic JUnit 5 annotations. If you are familier with JUnit 4, 
here you can notice some of the annotations changed in JUnit 5.

All the core annotations are located in ```org.junit.jupiter.api``` package in the ```junit-jupiter-api```.

### Basic annotations

  | Annotation  | Description|
  | ------------- | ------------- |
  | **@Test**  | This annotation denotes that a method is a test method. Unlike JUnit 4’s @Test annotation, this annotation does not declare any attributes.  |
  | **@DisplayName**  | Declares a custom display name for the test class or test method.   |
  | **@BeforeEach** | Denotes that the annotated method should be executed before each test. Exactly same as JUnit 4’s **@Before**.|
  | **@AfterEach** | Denotes that the annotated method should be executed after each test. Exactly same as JUnit 4’s **@After**.|
  | **@BeforeAll** | Denotes that the annotated method should be executed before all test methods in the current class. Exactly same as JUnit 4’s **@BeforeClass**. Such methods must be **static** (unless the “per-class” test instance lifecycle is used). |
  | **@AfterAll** | Denotes that the annotated method should be executed after all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class; analogous to JUnit 4’s **@AfterClass**. It must be static (unless the “per-class” test instance lifecycle is used).
  
Based on these basic annotations, lets see one running example: [LifecycleJUnit5Test.java](LifecycleJUnit5Test.java)

JUnit Jupiter allows @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, 
@TestTemplate, @BeforeEach, and @AfterEach to be declared on interface default methods. 

**@ParameterizedTest**:
Denotes that a method is a parameterized test. Parameterized tests make it possible to run a test multiple times with different arguments.
They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead.

**Must declare at least one source that will provide the arguments for each invocation and then consume the arguments in the test method.**

```@ValueSource``` : Annotation to specify a String array as the source of arguments.

```
@ParameterizedTest
@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
void palindromes(String candidate) {
    assertTrue(isPalindrome(candidate));
}

```
Check [@ParameterizedTest example]()