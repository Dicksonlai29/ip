package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand extends Command {
    private String title;

    public ToDoCommand(String title) {
        super(false);
        this.title = title;

    }
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) throws IOException {
        Task newTask = new ToDo(title);
        int taskListSizeBefore = taskList.size();
        taskList.add(newTask);
        memory.addNewTask(newTask);
        assert taskList.size() == taskListSizeBefore + 1 : "The number of task" +
                "in the task list is supposed to be incremented by exactly 1.";
        return "Got it. I've added this task: \n" +
                newTask + "\n" +
                "Now you have "+ taskList.size() +" tasks in the list." + "\n";
    }

}
