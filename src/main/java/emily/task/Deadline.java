package emily.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

}
