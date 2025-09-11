//components
import nusyapbot.command.Command;
import nusyapbot.components.CommandHandler;
import nusyapbot.components.Memory;
import nusyapbot.components.Parser;
import nusyapbot.components.Ui;
//tasktype
import nusyapbot.exceptions.*;
import nusyapbot.tasktype.Task;
//exceptions

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
    private ArrayList<Task> taskList;
    private Memory memory;
    private static String STORAGE_PATH = "./data/data.txt";

    public NUSYapBot() {
        taskList = new ArrayList<>();
        memory = new Memory(STORAGE_PATH);
    }

    public void run() throws IOException, NUSYapBotException {
        boolean isRunning = true;
        Ui.printWelcomeMessage(taskList, memory);

        while (isRunning) {
            String answer = Ui.readInput();
            // Step 1: Extract command type
            Command command = Parser.parse(answer);
            // Step 2: Execute the command and save response to taskList
            String response = command.execute(taskList, memory);
            // Step 3: Save changes
            isRunning = command.getIsBye();

            //Different command type & their procedure
            if (answer.startsWith("mark")) {
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

    public static void main(String[] args) {
        try {
            new NUSYapBot().run();
        } catch (NUSYapBotException | IOException e) {
            e.printStackTrace();
        }
    }

    // Solution below adapted from @@author pei886
    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            // Step 1: Extract command type
            Command command = Parser.parse(input);
            // Step 2: Execute the command and save response
            // to taskList & memory
            String response = command.execute(taskList, memory);
            // Step 4: Return response to UI
            return response;
        } catch (NUSYapBotException | IOException e) {
            return e.getMessage();
        }
    }
}
