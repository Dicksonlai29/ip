package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;

public class findCommand extends Command {
    private String keyword;

    public findCommand(String keyword) {
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
