package learning.JUnit5.annotations.examples.customAnnotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test custom annotation")
public class CustomConditionDemoTest {

    @Test
    @DisabledOnMac
    void testCustomAnnotaion(){
        assertEquals(6, (3+3), "Sum should be 6");
    }
}
