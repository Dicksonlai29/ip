//components
import nusyapbot.command.Command;
import nusyapbot.components.Memory;
import nusyapbot.components.Parser;
import nusyapbot.components.Ui;
//tasktype
import nusyapbot.exceptions.*;
import nusyapbot.tasktype.Task;
//exceptions

import java.util.ArrayList;

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
        taskList = memory.getTaskList();

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
            System.out.println(response);
            // Step 3: Check if loop should continue
            isRunning = !command.getIsBye();
            System.out.println(command.getIsBye());
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
