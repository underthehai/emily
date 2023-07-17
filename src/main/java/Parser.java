import java.util.ArrayList;

import exception.EmptyCommandException;
import exception.InvalidCommandException;

public class Parser {
    public Parser() {

    }

    public String[] parseInput(String input) throws EmptyCommandException, InvalidCommandException {
        if (input == null) {
            throw new EmptyCommandException();
        }

        ArrayList<String> tokens = new ArrayList<>();

        // type, desc, date
        String[] first_tok = input.split(" ", 2);
        String task_type;

        task_type = first_tok[0];
        tokens.add(task_type);

        if (first_tok.length == 1) {
            if (!(task_type.equals("exit") || task_type.equals("list"))) {
                throw new InvalidCommandException();
            }
        } else if (task_type.equals("todo") || task_type.equals("deadline") || task_type.equals("event")) {
            String[] second_tok;

            if (task_type.equals("deadline")) {
                second_tok = first_tok[1].split("/by", 2);
                if (second_tok.length == 2) {
                    tokens.add(second_tok[0]);
                    tokens.add(second_tok[1]);
                } else {
                    throw new InvalidCommandException();
                }

            } else if (task_type.equals("event")) {
                second_tok = first_tok[1].split("/at", 2);
                if (second_tok.length == 2) {
                    tokens.add(second_tok[0]);
                    tokens.add(second_tok[1]);
                } else {
                    throw new InvalidCommandException();
                }
            } else {
                tokens.add(first_tok[1]);
            }

        } else if (task_type.equals("delete") || task_type.equals("done") || task_type.equals("find")) {

            tokens.add(first_tok[1]);
        } else {
            throw new InvalidCommandException();
        }

        String[] arr = new String[tokens.size()];
        arr = tokens.toArray(arr);

        return arr;
    }

}
