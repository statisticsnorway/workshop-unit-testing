package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.*;

public class NestedDemoTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("@BeforeAll - Outer Class");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("@AfterAll - Outer Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("@BeforeEach - Outer Class");
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("@AfterEach - Outer Class");
    }

    @Test
    void test() {
        System.out.println("Outer Class Test");
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class InnerClass {
        @BeforeAll
        void setUpBeforeClassInner() throws Exception {
            System.out.println("@BeforeAll - Inner Class");
        }

        @AfterAll
        void tearDownAfterClassInner() throws Exception {
            System.out.println("@AfterAll - Inner Class");
        }

        @BeforeEach
        void setUp() throws Exception {
            System.out.println("@BeforeEach - Inner Class");
        }

        @AfterEach
        void tearDown() throws Exception {
            System.out.println("@AfterEach - Inner Class");
        }

        @Test
        void inner_test() {
            System.out.println("Inner Class test method");
        }
    }
}
