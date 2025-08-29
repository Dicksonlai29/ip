//components
import nusyapbot.components.CommandHandler;
import nusyapbot.components.Memory;
import nusyapbot.components.Ui;
//tasktype
import nusyapbot.tasktype.Task;
import nusyapbot.tasktype.ToDo;
import nusyapbot.tasktype.Deadline;
import nusyapbot.tasktype.Event;
//exceptions
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.UnrecognisedCommandException;
import nusyapbot.exceptions.DateFormatException;
import nusyapbot.exceptions.InvalidTaskException;

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

    

    public static void main(String[] args) {
        boolean flag = true;
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            taskList = Memory.getTaskList(STORAGE_PATH);
        } catch (IOException e) {
            System.out.println("Memory retrieval error. Please try again.");
        }
        
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
                    CommandHandler.delete(taskList, answer);
          
                    //rewrite the hard disk file
                    Memory.rewriteMemory(taskList, STORAGE_PATH);

                } catch (LackingInputException | InvalidTaskException | IOException e) {
                    System.out.println(e);
                }
            }

            else {
                try {
                    Task newTask = CommandHandler.createTask(answer);
                    taskList.add(newTask);
                    Memory.addNewTask(newTask, STORAGE_PATH);
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
