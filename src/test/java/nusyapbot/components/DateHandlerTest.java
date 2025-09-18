package nusyapbot.components;
import static org.junit.jupiter.api.Assertions.*;

import nusyapbot.exceptions.DateFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DateHandlerTest {

    @Test
    void testValidShortDateTime() throws Exception {
        LocalDateTime result = DateHandler.saveAsDateTime("29-08-25 1430");
        assertEquals(LocalDateTime.of(2025, 8, 29, 14, 30), result);
    }

    @Test
    void testValidLongDateTime() throws Exception {
        LocalDateTime result = DateHandler.saveAsDateTime("29-08-2025 1430");
        assertEquals(LocalDateTime.of(2025, 8, 29, 14, 30), result);
    }

    @Test
    void testValidShortDateOnly() throws Exception {
        LocalDateTime result = DateHandler.saveAsDateTime("29-08-25");
        assertEquals(LocalDateTime.of(2025, 8, 29, 0, 0), result);
    }

    @Test
    void testValidLongDateOnly() throws Exception {
        LocalDateTime result = DateHandler.saveAsDateTime("29-08-2025");
        assertEquals(LocalDateTime.of(2025, 8, 29, 0, 0), result);
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(DateFormatException.class,
                () -> DateHandler.saveAsDateTime("2025/08/29 1430"));
    }

    @Test
    void testNonsenseInput() {
        assertThrows(DateFormatException.class,
                () -> DateHandler.saveAsDateTime("hello world"));
    }

    @Test
    void testEmptyInput() {
        assertThrows(DateFormatException.class,
                () -> DateHandler.saveAsDateTime(""));
    }
}
