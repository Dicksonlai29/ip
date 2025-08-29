package nusyapbot.components;
//tasktype
import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.Event;
//exceptions
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.UnrecognisedCommandException;
import nusyapbot.exceptions.DateFormatException;
import nusyapbot.exceptions.InvalidTaskException;

import java.util.ArrayList;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandHandlerTest {
    @Test
    public void testCreateTask()
            throws LackingInputException, UnrecognisedCommandException, DateFormatException{
        String ans = "todo set up call";
        ToDo expectedToDo = new ToDo("set up call");
        assertEquals(expectedToDo, CommandHandler.createTask(ans));
    }
}
