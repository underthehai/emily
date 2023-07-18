package emily.command;

import emily.data.TaskHandler;

public class ListCommand extends Command {
    public ListCommand(TaskHandler handler) {
        super(handler);
    }

    @Override
    public String execute() {
        return this.handler.listItems();
    }

}
