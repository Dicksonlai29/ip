import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class NUSYapBot {
    private static String STORAGE_PATH = "../../../data/data.txt";

    public static void printAddTaskMessage(ArrayList<Task> taskList, int pointer) {
        System.out.println( "_________________________________" + "\n" +
                "Got it. I've added this task: " +
                taskList.get(pointer - 1) + "\n" +
                "Now you have "+ pointer +" tasks in the list." + "\n" +
                "_________________________________");
    };

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
                        taskList.add(new Deadline(taskDetail[2], 
                                taskDetail[3], taskDetail[1].equals("T")));

                    } else if (taskDetail[0].equals("E")) {
                        taskList.add(new Event(taskDetail[2], taskDetail[3], 
                                taskDetail[4], taskDetail[1].equals("T")));
                    }
                    
                }
            } 
            
        } catch (IOException e) {
            System.out.println(e);
            
        }
        return taskList;
    }

    public static void addTask(Task task) throws IOException {
        
        FileWriter fw = new FileWriter(STORAGE_PATH, true);
        //Format: Type | T/F | title | other-var
        String line = task.getType() + " | "  
                + (task.getIsCompleted() ? "T" : "F")
                + " | " + task.getTitle();
        if (task.getType() == 'D') {
            Deadline taskD = (Deadline) task;
            line += " | " + taskD.getDeadline();
        } else if (task.getType() == 'E') {
            Event taskE = (Event) task;
            line += " | " + taskE.getStartDate() + " | " + taskE.getEndDate();
        } 
        fw.write(line + "\n");
        fw.close();
    }

    

    public static void main(String[] args) {
        String welcome = """
                         ________________________________
                         Hello! I'm NUSYapBot!
                         What can I do for you?
                        _________________________________
                        """;

        String end = """
                     _________________________________
                     Goodbye. See you!
                     _________________________________
                     """;
            
        System.out.println(welcome);
        boolean flag = true;
        ArrayList<Task> taskList = getTaskList();
        int pointer = taskList.size();
        
        for (int i = 0; i < pointer; i++) {
            System.out.println(taskList.get(i));
        }
        
        Scanner input = new Scanner(System.in);

        while (flag) {
            String answer = input.nextLine();

            if (answer.equals("list")) {
                System.out.println("_________________________________");
                for (int i = 0; i < pointer; i++) {
                    System.out.println(taskList.get(i));
                }
                System.out.println("_________________________________");
            } else if (answer.equals("bye")) {
                flag = false;
                System.out.println(end);

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
                } catch (LackingInputException | InvalidTaskException e) {
                    System.out.println(e);
                }
            }

            else {
                try {
                    Task newTask = CommandHandler.createTask(answer);
                    taskList.add(newTask);
                    pointer++;
                    addTask(newTask);
                    printAddTaskMessage(taskList, pointer);

                } catch (LackingInputException | UnrecognisedCommandException e) {
                    System.out.println(e);
                } catch (IOException e) {

                }

            }
        }
    }
}
