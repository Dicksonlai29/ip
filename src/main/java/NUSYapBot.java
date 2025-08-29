//components
import nusyapbot.components.CommandHandler;
import nusyapbot.components.Memory;
import nusyapbot.components.Ui;
//tasktype
import nusyapbot.tasktype.Task;

//exceptions
import nusyapbot.exceptions.LackingInputException;
import nusyapbot.exceptions.UnrecognisedCommandException;
import nusyapbot.exceptions.DateFormatException;
import nusyapbot.exceptions.InvalidTaskException;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;

/**
 * Main class for the NUSYapBot program.
 * <p>
 * This bot allows users to create, manage, and save tasks.
 * Tasks are stored in a text file so they can be retrieved
 * the next time the program is run.
 */
public class NUSYapBot {
    private static String STORAGE_PATH = "./data/data.txt";

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
            } else if (answer.startsWith("find")) {
                CommandHandler.find(taskList, answer);
            } else {
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
