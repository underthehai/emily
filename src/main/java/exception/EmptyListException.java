package exception;

public class EmptyListException extends Exception {
    public EmptyListException() {
        super("Empty list\n");
    }

}
