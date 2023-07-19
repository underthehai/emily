package emily.task;
import java.time.LocalDateTime;


public class Task implements Comparable<Task> {
    protected String description;
    protected boolean done;
    protected LocalDateTime date = LocalDateTime.MIN;

    public Task(String desc) {
        this.description = desc;
        this.done = false;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public int compareTo(Task t) {
        return this.date.compareTo(t.getDate());
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String status = this.done ? "[X]" : "[ ]";
        return status + " " + this.description;
    }

}
