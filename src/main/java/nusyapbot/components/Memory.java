package nusyapbot.components;
//tasktype
import nusyapbot.command.Command;
import nusyapbot.command.DeadlineCommand;
import nusyapbot.command.EventCommand;
import nusyapbot.command.ToDoCommand;
import nusyapbot.exceptions.NUSYapBotException;
import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.Event;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memory {
    private String storageLocation;

    public Memory(String loc) {
        this.storageLocation = loc;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    /**
     * Loads the list of tasks from the storage file.
     * <p>
     * If the file exists, it will read each line and convert them
     * back into Task objects (ToDo, Deadline, or Event).
     * If the file does not exist, a new empty list is returned.
     *
     * @return an ArrayList containing all tasks read from the file
     */
    public ArrayList<Task> getTaskList() {
        //save task from hard disk to an arrayList
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            //load the task list saved previously
            File f = new File(storageLocation);
            if (f.createNewFile()) {
                return taskList;
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Command command;
                String taskLine = s.nextLine();
                //Format: Type | T/F | title | other-var
                String[] taskDetail = taskLine.split(" \\| ");
                assert taskDetail.length >= 3 : "Invalid task format in file: " + taskLine;

                String title = taskDetail[2];

                if (taskDetail[0].equals("T")) {
                    command = new ToDoCommand(title);

                } else if (taskDetail[0].equals("D")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime date = LocalDateTime.parse(taskDetail[3], formatter);

                    command = new DeadlineCommand(title, date);

                } else if (taskDetail[0].equals("E")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime start = LocalDateTime.parse(taskDetail[3], formatter);
                    LocalDateTime end = LocalDateTime.parse(taskDetail[4], formatter);

                    command = new EventCommand(title, start, end);
                } else {
                    throw new NUSYapBotException("Data is stored in wrong format in storage.");
                }

                command.execute(taskList, this);


            }
            s.close();
        } catch (IOException | NUSYapBotException e) {
            e.printStackTrace();
        }
        return taskList;
    }
    /**
     * Formatting the task object into String and write to file
     * <p>
     * The task is written in a specific format depending on its type
     * (ToDo, Deadline, or Event).
     *
     * @param task the task object to be added
     * @throws IOException if the file cannot be written
     */
    public void addTask(FileWriter fw, Task task) throws IOException {
        //Format: Type | T/F | title | other-var
        String line = task.getType() + " | "  
                + (task.getIsCompleted() ? "T" : "F")
                + " | " + task.getTitle();
        if (task.getType() == 'D') {
            Deadline taskD = (Deadline) task;
            String ddl = taskD.getDeadline().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            line += " | " + ddl;
        } else if (task.getType() == 'E') {
            Event taskE = (Event) task;
            String start = taskE.getStartDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            String end = taskE.getEndDate().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));

            line += " | " + start + " | " + end;
        } 
        fw.write(line + "\n");
    }

    /**
     * Saves a new task by appending it to the storage file.
     * <p>
     * The task is written in a specific format depending on its type
     * (ToDo, Deadline, or Event).
     *
     * @param task the task object to be added
     * @throws IOException if the file cannot be written
     */
    public void addNewTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(storageLocation, true);
        addTask(fw, task);
        fw.close();
    }

    /**
     * Overwrite the existing storage file with the current list of task.
     * <p>
     * The task is written in a specific format depending on its type
     * (ToDo, Deadline, or Event).
     *
     * @param Task the task object to be added
     * @throws IOException if the file cannot be written
     */
    public void rewriteMemory(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storageLocation, false); //false indicate overwrite mode
        for (Task task: taskList) {
            this.addTask(fw, task);
        }
        fw.close();
    }

}