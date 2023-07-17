import java.io.File;
import java.io.FileNotFoundException;
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
                        Event e = new Event(toks[2], toks[3]);
                        if (done) {
                            e.markDone();
                        }
                        arr.add(e);
                    case "D":
                        Deadline d = new Deadline(toks[2], toks[3]);
                        if (done) {
                            d.markDone();
                        }
                        arr.add(d);
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
