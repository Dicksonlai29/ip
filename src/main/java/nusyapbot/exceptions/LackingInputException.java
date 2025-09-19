package nusyapbot.exceptions;

/**
 * Represents an exception thrown when a required input is missing or left empty.
 */
public class LackingInputException extends NUSYapBotException {
    public LackingInputException(String inputMissing) {
        super(inputMissing + " cannot be left empty. " +
                "\nAction failed.");
    }
}
