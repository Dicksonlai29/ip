package nusyapbot.exceptions;

public class UnrecognisedCommandException extends NUSYapBotException {
    public UnrecognisedCommandException() {
        super("Sorry, I'm not too sure what you mean by that... Please try again.");
    }
}
