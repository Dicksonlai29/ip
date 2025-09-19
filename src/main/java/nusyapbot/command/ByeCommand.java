package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Task;

import java.util.ArrayList;

/**
 * Represents a command to exit the chatbot application.
 * When executed, this command returns a farewell message and signals the application to terminate.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }
    
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) {
        return "It's nice talking to you. See you!";
    }

    @Override
    public boolean getIsBye() {
        return true;
    }

}
