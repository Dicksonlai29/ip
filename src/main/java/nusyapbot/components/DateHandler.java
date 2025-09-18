package nusyapbot.components;

import nusyapbot.exceptions.DateFormatException;

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

    public static LocalDateTime saveAsDateTime(
            String dateString) throws DateFormatException {

//        for (DateTimeFormatter formatter: formatters) {
//            try {
//                if (dateString.contains(" ")) {
//                    return LocalDateTime.parse(dateString, formatter);
//                } else {
//                    // if it’s just a date (no time), default time 00:00
//                    return LocalDate.parse(dateString, formatter).atStartOfDay();
//                }
//            } catch (DateTimeParseException e) {
//
//            }
//        }
        boolean hasTime = dateString.contains(" ");
        int length = dateString.length();
        try {
            if (hasTime && length == 13) { //dd-MM-yy HHmm
                return LocalDateTime.parse(dateString, formatters.get(0));

            } else if (hasTime && length == 15) { //dd-MM-yyyy HHmm
                return LocalDateTime.parse(dateString, formatters.get(1));

            } else if (!hasTime && length == 8) { //dd-MM-yy
                return LocalDate.parse(dateString, formatters.get(2))
                        .atStartOfDay(); // if it’s just a date (no time), default time 00:00

            } else if (!hasTime && length == 10) { //dd-MM-yyyy
                return LocalDate.parse(dateString, formatters.get(3))
                        .atStartOfDay(); // if it’s just a date (no time), default time 00:00

            } else {
                throw new DateFormatException();
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }
}
