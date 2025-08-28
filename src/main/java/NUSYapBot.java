import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NUSYapBot {
    private static String STORAGE_PATH = "../../../data/data.txt";

    

    public static ArrayList<Task> getTaskList() {
        //save task from hard disk to an arrayList
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            //load the task list saved previously
            File f = new File(STORAGE_PATH);

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
            
        } catch (IOException e) {
            System.out.println(e);
            
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

    public static void addNewTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(STORAGE_PATH, true);
        addTask(fw, task);
        fw.close();
    }

    public static void rewriteMemory(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(STORAGE_PATH, false); //false indicate overwrite mode
        for (Task task: taskList) {
            addTask(fw, task);
        }
        fw.close();
    }

    public static void main(String[] args) {
        boolean flag = true;
        ArrayList<Task> taskList = getTaskList();
        int pointer = taskList.size();
        
        Ui.printWelcomeMessage(taskList);
        
        Scanner input = new Scanner(System.in);

        while (flag) {
            String answer = input.nextLine();

            if (answer.equals("list")) {
                System.out.println("_________________________________");
                Ui.printTaskList(taskList);
                System.out.println("_________________________________");
            } else if (answer.equals("bye")) {
                flag = false;
                Ui.printGoodbyeMessage();

            } else if (answer.startsWith("mark")) {
                try {
                    CommandHandler.markTask(taskList, answer, true, STORAGE_PATH);
                } catch (LackingInputException | InvalidTaskException | IOException e) {
                    System.out.println(e);
                }

            } else if (answer.startsWith("unmark")) {
                try {
                    CommandHandler.markTask(taskList, answer, false, STORAGE_PATH);
                } catch (LackingInputException | InvalidTaskException | IOException e) {
                    System.out.println(e);
                }
            } else if (answer.startsWith("delete")) {
                try {
                    CommandHandler.delete(taskList, answer, pointer);
                    pointer--;
                    //rewrite the hard disk file
                    rewriteMemory(taskList);

                } catch (LackingInputException | InvalidTaskException | IOException e) {
                    System.out.println(e);
                }
            }

            else {
                try {
                    Task newTask = CommandHandler.createTask(answer);
                    taskList.add(newTask);
                    pointer++;
                    addNewTask(newTask);
                    Ui.printAddTaskMessage(taskList);

                } catch (LackingInputException | UnrecognisedCommandException | DateFormatException e) {
                    System.out.println(e);
                } catch (IOException e) {
                    System.out.println(e);
                }

            }
        }
    }
}
