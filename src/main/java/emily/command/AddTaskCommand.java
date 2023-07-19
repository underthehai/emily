package emily.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import emily.data.TaskHandler;
import emily.task.Deadline;
import emily.task.Event;
import emily.task.Todo;

public class AddTaskCommand extends Command {
    String[] info;

    public AddTaskCommand(TaskHandler handler, String[] info) {
        super(handler);
        this.info = info;
    }

    @Override
    public String execute() {
        String type = info[0];

        switch (type) {
            case "todo":
                Todo todo = new Todo(info[1]);
                handler.addItem(todo);
                return "Adding a TODO task: " + todo.toString() + "\n";

            case "deadline":
                DateTimeFormatter deadline_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime deadline_time = LocalDateTime.parse((info[2] + " 00:00"), deadline_formatter);

                Deadline deadline = new Deadline(info[1], deadline_time);
                handler.addItem(deadline);
                return "Adding a DEADLINE task: " + deadline.toString() + "\n";

            case "event":
                DateTimeFormatter event_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime event_time = LocalDateTime.parse(info[2], event_formatter);
                Event event = new Event(info[1], event_time);
                handler.addItem(event);

                return "Adding an EVENT task: " + event.toString() + "\n";
            default:
                return "";
        }
    }
}
