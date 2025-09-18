package nusyapbot.components;
//tasktype
import nusyapbot.command.ByeCommand;
import nusyapbot.command.ListCommand;
import nusyapbot.tasktype.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String DIVIDER = "___________________________________\n";
    private static final Scanner input = new Scanner(System.in);

    public static String readInput() {
        return input.nextLine();
    }

    /**
     * Prints the default welcome note along with list of existing tasks.
     *
     * @param taskList the current list of tasks
     */
    public static void printWelcomeMessage(ArrayList<Task> taskList, Memory memory) {
        String welcome = """
                         Hello! I'm NUSYapBot!
                         What can I do for you?
                        """;

        System.out.println(DIVIDER +
                welcome +
                DIVIDER);

        printTaskList(taskList, memory);
    }

    /**
     * Prints the current list of tasks in a list format.
     *
     * @param taskList the current list of tasks
     */
    public static void printTaskList(ArrayList<Task> taskList, Memory memory) {
        String tasks = (new ListCommand()).execute(taskList, memory);
        System.out.println(tasks);
    }

}