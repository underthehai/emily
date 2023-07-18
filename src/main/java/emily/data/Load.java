package emily.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import emily.task.Deadline;
import emily.task.Event;
import emily.task.Task;
import emily.task.Todo;

import java.util.ArrayList;

public class Load {
    public Load() {

    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> arr = new ArrayList<>();

        try {
            File myObj = new File("resources/duke.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String toks[] = data.split("\\|");
                String type = toks[0];
                Boolean done = toks[1].equals("0") ? false : true;

                switch (type) {
                    case "T":
                        Todo t = new Todo(toks[2]);
                        if (done) {
                            t.markDone();
                        }
                        arr.add(t);
                        break;
                    case "E":
                        DateTimeFormatter datetime_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime event_datetime = LocalDateTime.parse(toks[3], datetime_formatter);
                        Event e = new Event(toks[2], event_datetime);
                        if (done) {
                            e.markDone();
                        }
                        arr.add(e);
                        break;
                    case "D":
                        DateTimeFormatter deadline_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate deadline_date = LocalDate.parse(toks[3], deadline_formatter);
                        Deadline d = new Deadline(toks[2], deadline_date);
                        if (done) {
                            d.markDone();
                        }
                        arr.add(d);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return arr;
    }

    public void saveData(ArrayList<Task> arr) {
        try {
            FileWriter myWriter = new FileWriter("resources/duke.txt", false);
            String description = "";
            String status = "";
            String line = "";
            for (Task t : arr) {
                description = t.getDescription();
                status = t.isDone() ? "1" : "0";

                if (t instanceof Todo) {
                    line = "T|" + status + "|" + description;
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    line = "D|" + status + "|" + description + "|" + d.getDate();

                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = "E|" + status + "|" + description + "|" + e.getDatetime();
                }

                myWriter.append(line + "\n");

            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

}
