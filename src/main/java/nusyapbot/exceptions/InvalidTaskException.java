package nusyapbot.exceptions;

public class InvalidTaskException extends NUSYapBotException {
    public InvalidTaskException() {
        super("Sorry, that task doesn't exist. Please try again.");
    }
}