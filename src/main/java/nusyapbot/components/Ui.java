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
    public static void printWelcomeMessage(ArrayList<Task> taskList) {
        String welcome = """
                         Hello! I'm NUSYapBot!
                         What can I do for you?
                        """;

        System.out.println(DIVIDER +
                welcome +
                DIVIDER);

        printTaskList(taskList);
    }

    /**
     * Prints the current list of tasks in a list format.
     *
     * @param taskList the current list of tasks
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        String tasks = (new ListCommand()).execute(taskList);
        System.out.println(tasks);
    }

    /**
     * Prints a message to confirm that a task has been added.
     *
     * @param taskList the current list of tasks
     */
    public static void printAddTaskMessage(ArrayList<Task> taskList) {
        int numOfTask = taskList.size();
        System.out.println( "_________________________________" + "\n" +
                "Got it. I've added this task: " +
                taskList.get(numOfTask - 1) + "\n" +
                "Now you have "+ numOfTask +" tasks in the list." + "\n" +
                "_________________________________");
    };
}