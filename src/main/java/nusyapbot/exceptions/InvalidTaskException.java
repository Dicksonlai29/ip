package nusyapbot.exceptions;

/**
 * Represents an exception thrown when an invalid task is referenced in the application.
 * <p>
 * This exception indicates that the requested task does not exist,
 * prompting the user to try again with a valid task.
 */
public class InvalidTaskException extends NUSYapBotException {
    public InvalidTaskException() {
        super("Sorry, that task doesn't exist. Please try again.");
    }
}