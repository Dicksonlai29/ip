public class NUSYapBotException extends Exception {
    public NUSYapBotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "NUSYapBot ran into error: " + getMessage();
    }
}
