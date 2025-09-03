package nusyapbot.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateHandler {
    private static final List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("dd-MM-yy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
    );

    public static LocalDateTime saveAsDateTime(String dateString) {
        for (DateTimeFormatter formatter: formatters) {
            try {
                if (dateString.contains(" ")) {
                    return LocalDateTime.parse(dateString, formatter);
                } else {
                    // if it’s just a date (no time), default time 00:00

                    return LocalDate.parse(dateString, formatter).atStartOfDay();
                }
            } catch (DateTimeParseException e) {
                //do nothing
            }
        }
        throw new IllegalArgumentException("Invalid dateTime format!");
    }
}
