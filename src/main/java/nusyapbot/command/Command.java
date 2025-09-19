package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an abstract command that can be executed by the bot.
 * Each command may indicate whether it is a termination command (bye).
 */
public abstract class Command {
    private boolean isBye;

    public Command(boolean isBye) {
        this.isBye = isBye;
    }
    
    /**
     * Executes the command using the provided task list and memory.
     *
     * @param tasklist The list of tasks to operate on.
     * @param memory The memory object for persistent storage or retrieval.
     * @return A string representing the result of the command execution.
     * @throws NUSYapBotException If an error specific to NUSYapBot occurs during execution.
     * @throws IOException If an I/O error occurs during execution.
     */
    public abstract String execute(ArrayList<Task> tasklist, Memory memory)
            throws NUSYapBotException, IOException;

    public boolean getIsBye() {
        return this.isBye;
    }
}
