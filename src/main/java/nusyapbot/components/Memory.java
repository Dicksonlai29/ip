package nusyapbot.components;
//tasktype
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

    public static ArrayList<Task> getTaskList(String loc) throws IOException {
        //save task from hard disk to an arrayList
        ArrayList<Task> taskList = new ArrayList<>();
        
        //load the task list saved previously
        File f = new File(loc);
        if (!f.createNewFile()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskLine = s.nextLine();
                //Format: Type | T/F | title | other-var
                String[] taskDetail = taskLine.split(" \\| ");
                
                if (taskDetail[0].equals("T")) {
                    taskList.add(new ToDo(taskDetail[2], 
                            taskDetail[1].equals("T")));
                    
                } else if (taskDetail[0].equals("D")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime date = LocalDateTime.parse(taskDetail[3], formatter);
                    
                    taskList.add(new Deadline(taskDetail[2], 
                            date, taskDetail[1].equals("T")));

                } else if (taskDetail[0].equals("E")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime start = LocalDateTime.parse(taskDetail[3], formatter);
                    LocalDateTime end = LocalDateTime.parse(taskDetail[4], formatter);

                    taskList.add(new Event(taskDetail[2], start, end, taskDetail[1].equals("T")));
                }
                
            }
        } 
        return taskList;
    }

    public static void addTask(FileWriter fw, Task task) throws IOException {
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

    public static void addNewTask(Task task, String loc) throws IOException {
        FileWriter fw = new FileWriter(loc, true);
        addTask(fw, task);
        fw.close();
    }

    public static void rewriteMemory(ArrayList<Task> taskList, String loc) throws IOException {
        FileWriter fw = new FileWriter(loc, false); //false indicate overwrite mode
        for (Task task: taskList) {
            addTask(fw, task);
        }
        fw.close();
    }

}