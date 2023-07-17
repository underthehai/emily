package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Load {
    public Load() {

    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> arr = new ArrayList<>();

        try {
            File myObj = new File("data/duke.txt");
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

    }

}
