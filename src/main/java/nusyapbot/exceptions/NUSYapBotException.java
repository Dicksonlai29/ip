package nusyapbot.exceptions;

/**
 * Represents a custom exception specific to the NUSYapBot application.
 * This exception is thrown when the bot encounters an error during execution.
 */
public class NUSYapBotException extends Exception {
    public NUSYapBotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NUSYapBot ran into error: " + getMessage();
    }
}
