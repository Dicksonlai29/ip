package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    private boolean isBye;

    public Command(boolean isBye) {
        isBye = isBye;
    }
    public abstract String execute(ArrayList<Task> tasklist, Memory memory)
            throws NUSYapBotException, IOException;

    public boolean getIsBye() {
        return isBye;
    }
}
