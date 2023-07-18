package emily.exception;

public class EmptyCommandException extends Exception {

    public EmptyCommandException() {
        super("Please input a command");
    }
}
