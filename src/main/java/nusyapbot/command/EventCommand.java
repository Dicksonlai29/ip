package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.Event;
import nusyapbot.tasktype.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventCommand extends Command {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventCommand(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(false);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;

    }
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) throws IOException {
        Task newTask = new Event(title, startDate, endDate);
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
