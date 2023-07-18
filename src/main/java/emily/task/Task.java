package emily.task;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String desc) {
        this.description = desc;
        this.done = false;
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
