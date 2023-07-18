package emily.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Oops, I do not recognise the command\n");
    }
}
