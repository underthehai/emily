package emily;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import emily.command.*;
import emily.data.TaskHandler;
import emily.exception.EmptyCommandException;
import emily.exception.EmptyListException;
import emily.exception.InvalidCommandException;
import emily.exception.TaskNotFoundException;

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
            File myObj = new File("resources/duke.txt");
            store = new TaskHandler();

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("Reading from duke.txt");
                store.loadTasks();

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
                String input_command = tokens[0];
                Command command = new Command(store);

                if (tokens.length == 1) {
                    if(input_command.equals("list") || input_command.equals("sort")) {
                        boolean sorted = input_command.equals("sort");
                        command = new ListCommand(store, sorted);
                    } 
                    else if (input_command.equals("exit")) {
                        terminator = false;
                        break;
                    }
                    else {
                        throw new InvalidCommandException();
                    }
                } else {
                    if (input_command.equals("done") || input_command.equals("find")
                            || input_command.equals("delete")) {
                        command = new ManageTaskCommand(store, input_command, tokens[1]);
                    } else {
                        command = new AddTaskCommand(store, tokens);
                    }

                }
                response = command.execute();

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
        store.saveTasks();

    }

    public static void main(String[] args) {
        initialise();
        System.out.println(INTRO);
        interaction();
        System.out.println(OUTRO);

    }
}
