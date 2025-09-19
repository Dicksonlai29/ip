package nusyapbot.exceptions;

/**
 * Represents an exception thrown when a date string does not match any of the accepted formats.
 */
public class DateFormatException extends NUSYapBotException {
    public DateFormatException() {
        super("\nDate is not in any of the following form:\n" +
                "1. DD-MM-YYYY TTTT\n" +
                "2. DD-MM-YYYY\n" +
                "3. DD-MM-YY TTTT\n" +
                "4. DD-MM-YY\n" +
                "Time, if given, should be in 24hr format.");
    }
}
