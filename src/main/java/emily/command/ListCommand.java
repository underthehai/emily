package emily.command;

import emily.data.TaskHandler;

public class ListCommand extends Command {
    boolean sorted = false;

    public ListCommand(TaskHandler handler, boolean sorted) {
        super(handler);
        this.sorted = sorted;
    }

    @Override
    public String execute() {
        if(this.sorted) {
            this.handler.sortTaskArray();
        }
        return this.handler.listItems();
    }

}
