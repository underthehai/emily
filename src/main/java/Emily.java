import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import exception.EmptyCommandException;
import exception.EmptyListException;
import exception.InvalidCommandException;
import exception.TaskNotFoundException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Emily {
    private static String LINE = "================\n";

    private static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String INTRO = "Hello from\n" + LOGO + LINE + "Hello I am Duke\nWhat can I do for you?\n"
            + LINE;
    private static String OUTRO = "Goodbye, Duke exiting\n";

    private static TaskHandler store;

    public static void initialise() {
        try {
            File myObj = new File("data/duke.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Reading from duke.txt");
                store = new TaskHandler();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void interaction() {
        Scanner sc = new Scanner(System.in);

        String input;
        boolean terminator = true;
        String response = "";
        Parser parser = new Parser();

        while (terminator) {
            input = sc.nextLine();
            try {

                String[] tokens = parser.parseInput(input);
                String command = tokens[0];
                if (tokens.length == 1) {
                    switch (command) {
                        case "list":
                            response = store.listItems() + "\n";
                            break;
                        case "exit":
                            terminator = false;
                            break;
                        default:
                            throw new InvalidCommandException();
                    }
                } else {
                    switch (command) {
                        case "done":
                            Task d = store.markDoneTask(tokens[1]);
                            response = "Mark Task: " + d.toString() + " as Done\n";
                            break;
                        case "todo":
                            response = "Adding a TODO task: " + tokens[1] + "\n";
                            store.addItem(new Todo(tokens[1]));
                            break;
                        case "deadline":
                            response = "Adding a DEADLINE task: " + tokens[1] + "\n";
                            store.addItem(new Deadline(tokens[1], tokens[2]));
                            break;
                        case "event":
                            response = "Adding an EVENT task: " + tokens[1] + "\n";
                            store.addItem(new Event(tokens[1], tokens[2]));
                            break;
                        case "delete":
                            Task deleted_task = store.removeItem(tokens[1]);
                            response = "Deleting Task: " + deleted_task.toString() + "\n";
                            break;
                        case "find":
                            Task[] find_task = store.findTasks(tokens[1]);
                            response = "Found Task(s):\n";

                            for (Task t : find_task) {
                                response += t.toString() + "\n";
                            }
                            break;

                        default:
                            throw new InvalidCommandException();
                    }
                }

                System.out.println(LINE + response + LINE);

            } catch (InvalidCommandException e) {
                System.out.println(LINE + e.getMessage() + LINE);
            } catch (TaskNotFoundException e) {
                System.out.println(LINE + e.getMessage() + LINE);
            } catch (EmptyListException e) {
                System.out.println(LINE + e.getMessage() + LINE);
            } catch (EmptyCommandException e) {
                System.out.println(LINE + e.getMessage() + LINE);

            }
        }
        sc.close();

    }

    public static void main(String[] args) {
        initialise();
        System.out.println(INTRO);
        interaction();
        System.out.println(OUTRO);

    }
}
