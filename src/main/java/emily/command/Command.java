package emily.command;

import emily.data.TaskHandler;
import emily.exception.EmptyListException;
import emily.exception.TaskNotFoundException;

public class Command {
    protected TaskHandler handler;

    public Command(TaskHandler handler) {
        this.handler = handler;

    }

    public String execute() throws EmptyListException, TaskNotFoundException {

        String response = "";
        return response;
    }

}
