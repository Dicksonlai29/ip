package nusyapbot.command;

import nusyapbot.components.Memory;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private String title;
    private LocalDateTime date;

    public DeadlineCommand(String title, LocalDateTime date) {
        super(false);
        this.title = title;
        this.date = date;

    }
    @Override
    public String execute(ArrayList<Task> taskList, Memory memory) throws IOException {
        Task newTask = new Deadline(title, date);
        taskList.add(newTask);
        memory.addNewTask(newTask);
        return "Got it. I've added this task: \n" +
                newTask + "\n" +
                "Now you have "+ taskList.size() +" tasks in the list." + "\n";
    }

}
