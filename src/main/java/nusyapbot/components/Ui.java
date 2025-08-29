package nusyapbot.components;
//tasktype
import nusyapbot.tasktype.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints the default welcome note along with list of existing tasks.
     *
     * @param taskList the current list of tasks
     */
    public static void printWelcomeMessage(ArrayList<Task> taskList) {
        String welcome = """
                         ________________________________
                         Hello! I'm NUSYapBot!
                         What can I do for you?
                        _________________________________
                        """;

        System.out.println(welcome);

        printTaskList(taskList);
    }

    /**
     * Prints the current list of tasks in a list format.
     *
     * @param taskList the current list of tasks
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i));
        }
    }

    /**
     * Prints the default end note when exiting the program.
     *
     */
    public static void printGoodbyeMessage() {
        String end = """
                     _________________________________
                     Goodbye. See you!
                     _________________________________
                     """;
        System.out.println(end);

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