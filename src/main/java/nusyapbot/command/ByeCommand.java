package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Task;

import java.util.ArrayList;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(false);
    }
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) {
        return "It's nice talking to you. See you!";
    }

}
