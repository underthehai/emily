package command;

import data.TaskHandler;
import exception.EmptyListException;
import exception.TaskNotFoundException;
import task.Task;

public class ManageTaskCommand extends Command {
    String id;
    String type;

    public ManageTaskCommand(TaskHandler handler, String type, String id) {
        super(handler);
        this.id = id;
        this.type = type;
    }

    @Override
    public String execute() throws EmptyListException, TaskNotFoundException {
        String response = "";
        switch (this.type) {
            case "done":
                Task d = handler.markDoneTask(id);
                response = "Mark Task: " + d.toString() + " as Done\n";
                break;
            case "find":
                Task[] find_task = handler.findTasks(id);
                response = "Found Task(s):\n";

                for (Task t : find_task) {
                    response += t.toString() + "\n";
                }
                break;
            case "delete":
                Task deleted_task = handler.removeItem(id);
                response = "Deleting Task: " + deleted_task.toString() + "\n";
                break;

        }
        return response;

    }

}
