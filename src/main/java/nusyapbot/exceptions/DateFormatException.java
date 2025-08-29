package nusyapbot.exceptions;

public class DateFormatException extends NUSYapBotException {
    public DateFormatException() {
        super("DateTime inputted not in the format of DD-MM-YYYY TTTT ");
    }
}
