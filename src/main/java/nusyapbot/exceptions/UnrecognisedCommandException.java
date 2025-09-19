package nusyapbot.exceptions;

/**
 * Represents an error thrown when the user enters 
 * a command that is not recognised by NUSYapBot.
 */
public class UnrecognisedCommandException extends NUSYapBotException {
    public UnrecognisedCommandException() {
        super("Sorry, I'm not too sure what you mean by that... Please try again.");
    }
}
