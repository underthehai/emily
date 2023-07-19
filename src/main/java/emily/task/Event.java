package emily.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }

    public String getDatetimeString() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"))
                + ")";
    }

}
