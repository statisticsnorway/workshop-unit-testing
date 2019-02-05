package learning.JUnit5.annotations.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@EnabledOnOs(OS.MAC)
public class ConditionalTestExecutionDemoTest {

    /*@DisplayName("Enable test on MAC")
    @Test
    @EnabledOnOs(OS.MAC)
    void verifyTestEnabledOnlyOnMac() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Disable test on MAC")
    @Test
    @DisabledOnOs(OS.MAC)
    void verifyTestDisabledOnWindows() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Enable test on Java 8")
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void verifyTestEnabledOnJre8() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Enable test on Java 9 or 10")
    @Test
    @EnabledOnJre({JRE.JAVA_9, JRE.JAVA_10})
    void verifyTestEnabledOnJre9Or10() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Enable test on Java 11")
    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void verifyTestEnabledOnJre11() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Enable test on 64 bit architecture")
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void verifyTestEnabledOnlyOn64BitArchitecture() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @DisplayName("Enable test on Ci server")
    @Test
    @EnabledIfSystemProperty(named = "ci-server", matches = "true")
    void verifyTestDisabledOnCiServer() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }
*/
    @Test
    @EnabledIf("2*3 == 6")
    void verifyTestExecutesOnlyIfConditionTrue() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @Test
    @DisabledIf("Math.random() < 0.314159")
    void verifyTestMightNotBeExecuted() {
        assertEquals(6, (3 + 3), "Sum should be 6");
    }

    @Test
    @DisabledIf("/32/.test(systemProperty.get('os.arch'))")
    void verifyTestDisabledOn32BitArchitecture() {
        assertFalse(System.getProperty("os.arch").contains("32"));
    }

    @Test
    @EnabledIf("'CI' == systemEnvironment.get('ENV')")
    void verifyTestOnlyExecutedOnCiServer() {
        assertTrue("CI".equals(System.getenv("ENV")));
    }

    @Test // Multi-line script, custom engine name and custom reason.
    @EnabledIf(value = {
            "load('nashorn:mozilla_compat.js')",
            "importPackage(java.time)",
            "",
            "var today = LocalDate.now()",
            "var tomorrow = today.plusDays(1)",
            "tomorrow.isAfter(today)"
    },
            engine = "nashorn",
            reason = "Self-fulfilling: {result}")
    void verifyTestExecutesOnlyIfDayIsAfterTomorrow() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertTrue(tomorrow.isAfter(today));
    }
}
