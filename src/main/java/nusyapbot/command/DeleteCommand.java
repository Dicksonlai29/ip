package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.InvalidTaskException;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * When executed, the command removes the corresponding
 * task from the provided task list, and updates the memory storage. If the input is
 * invalid or the task number does not exist, appropriate exceptions are thrown.
 */
public class DeleteCommand extends Command {
    private String taskNumber;


    public DeleteCommand(String taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(ArrayList<Task> taskList, Memory memory)
            throws NUSYapBotException, IOException {
        if (taskNumber.isBlank()) {
            throw new LackingInputException("task number");
        }

        try {
            int taskNum = Integer.parseInt(taskNumber) - 1;
            Task delTask = taskList.get(taskNum);
            taskList.remove(taskNum);
            memory.rewriteMemory(taskList);

            return "Noted. I've removed this task:\n" +
                    delTask + "\n" +
                    "Now you have " + taskList.size() + " tasks in the list.\n";




        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }
}
