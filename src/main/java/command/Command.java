package command;

import data.TaskHandler;
import exception.EmptyListException;
import exception.TaskNotFoundException;

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
