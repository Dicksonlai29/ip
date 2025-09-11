package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Task;

import java.util.ArrayList;

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
