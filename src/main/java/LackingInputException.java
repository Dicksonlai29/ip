public class LackingInputException extends NUSYapBotException {
    public LackingInputException(String inputMissing) {
        super(inputMissing + " cannot be left empty. " +
                "\nTask creation failed.");
    }
}
