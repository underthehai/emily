package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import org.junit.*;

import emily.Parser;
import emily.exception.EmptyCommandException;
import emily.exception.InvalidCommandException;

public class ParserTest {
    @Test
    public void testValidAddTaskCommand() throws Exception {
        Parser parser = new Parser();

        String valid1 = "todo some thing";
        String[] ans1 = new String[] { "todo", "some thing" };
        String[] test1 = parser.parseInput(valid1);

        String valid2 = "deadline some thing /by 2019-10-01";
        String[] ans2 = new String[] { "deadline", "some thing", "2019-10-01" };
        String[] test2 = parser.parseInput(valid2);

        String valid3 = "event some thing /at 2019-01-01 14:30";
        String[] ans3 = new String[] { "event", "some thing", "2019-01-01 14:30" };
        String[] test3 = parser.parseInput(valid3);

        assertArrayEquals(ans1, test1);
        assertArrayEquals(ans2, test2);
        assertArrayEquals(ans3, test3);

        String space_valid1 = "todo     some thing       ";
        String[] test4 = parser.parseInput(space_valid1);
        assertArrayEquals(ans1, test4);

        String space_valid2 = "deadline     some thing     /by     2019-10-01    ";
        String[] test5 = parser.parseInput(space_valid2);
        assertArrayEquals(ans2, test5);

    }

    @Test()
    public void testInvalidAddTask() throws Exception {
        Parser parser = new Parser();

        String invalid_command = "something something";
        String empty_command = "";

        assertThrows(InvalidCommandException.class, () -> parser.parseInput(invalid_command));
        assertThrows(EmptyCommandException.class, () -> parser.parseInput(empty_command));

        String invalid_deadline = "deadline something /at 2019-01-01";
        String invalid_event = "event something /by 2019-01-01 12:00";

        assertThrows(InvalidCommandException.class, () -> parser.parseInput(invalid_deadline));
        assertThrows(InvalidCommandException.class, () -> parser.parseInput(invalid_event));

    }

}
