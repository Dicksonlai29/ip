package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Task;

import java.util.ArrayList;

/**
 * Represents a command that lists all tasks in the task list.
 * When executed, it returns a string representation of all tasks,
 * each separated by a newline.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }
    
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

}
