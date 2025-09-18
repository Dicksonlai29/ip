package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.InvalidTaskException;
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String taskNumber;
    private boolean status;

    public DeleteCommand(String taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
        this.status = status;
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
