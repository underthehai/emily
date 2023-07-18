package emily.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String desc) {
        super("Task " + desc + " is not found\n");
    }
}
