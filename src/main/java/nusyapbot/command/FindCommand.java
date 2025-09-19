package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Represents a command to find tasks in the task list that contain a specified keyword in their title.
 * <p>
 * The {@code FindCommand} searches through the provided list of tasks and collects all tasks whose titles
 * contain the given keyword. It then returns a message listing all matching tasks, or a message indicating
 * that no matches were found.
 * </p>
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }
    
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory)
            throws NUSYapBotException, IOException {
        ArrayList<Task> matchedList = new ArrayList<>();
        StringBuilder message = new StringBuilder();

        for (Task task: taskList) {
            if (task.getTitle().contains(keyword)) {
                matchedList.add(task);
            }
        }

        if (matchedList.isEmpty()) {
            message = new StringBuilder("Sorry, there's no matching tasks in your list.");
        } else {
            message.append("There's a total of ").append(matchedList.size()).append(" matching tasks found:\n");
            for (Task task: matchedList) {
                message.append(task).append("\n");
            }
        }

        return message.toString();
    }
}
