package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test tagging")
public class TaggingDemoTest {

    @Test
    @Tag("production")
    @Tag("fast")
    public void testCaseA(){
        assertEquals(6, (3+3), "Sum should be 6");
    }

    @Test
    @Tag("slow")
    @Tag("production")
    public void testCaseB(){
        assertEquals(9, (3*3), "Result should be 9");
    }

    @Test
    @Tag("test")
    @Tag("fast")
    public void testCaseC(){
        assertEquals(9, (3*3), "Result should be 9");
    }

    @Test
    @Tag("test")
    @Tag("slow")
    public void testCaseD(){
        assertEquals(9, (3*3), "Result should be 9");
    }

}
