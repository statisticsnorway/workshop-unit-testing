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
  
   
In this section we will look at some special annotations like: @DisplayName, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate.


## **@DisplayNames**

Test classes and test methods can declare custom display names — with spaces, special characters, 
and even emojis — that will be displayed by test runners and test reporting.




## **@Disabled**
Entire test classes or individual test methods may be disabled via the **```@Disabled```** annotation.
Junit 4 had ```@Ignore``` annotation.


Based on these basic annotations, lets see one running example: [LifecycleJUnit5Test.java](examples/LifecycleJUnit5Test.java)


Lets move next to [ParameterizedTest](parameterizedTest.md)