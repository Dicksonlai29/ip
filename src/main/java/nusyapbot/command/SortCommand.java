package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.exceptions.UnrecognisedCommandException;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.util.*;

public class SortCommand extends Command {
    private String field;

    public SortCommand(String field) {
        super(false);
        this.field = field;
    }
    @Override
    public String execute(
            ArrayList<Task> taskList, Memory memory) throws NUSYapBotException, IOException {

        //provide three field to sort only
        Map<String, Comparator<Task>> comparators = new HashMap<>();
        comparators.put("title", Comparator.comparing(Task::getTitle));
        comparators.put("status", Comparator.comparing(Task::getIsCompleted));
        comparators.put("type", Comparator.comparing(Task::getType));

        // Get the comparator for the chosen field
        Comparator<Task> comparator = comparators.get(field);

        if (comparator == null) {
            throw new NUSYapBotException("Invalid sort field: " + field);
        }

        //sort the list
        taskList.sort(comparator);

       //print it with ListCommand
        Command listCommand = new ListCommand();
        return listCommand.execute(taskList, memory);
    }

}
