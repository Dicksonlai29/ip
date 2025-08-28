import java.util.ArrayList;

public class Ui {
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

    public static void printTaskList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(taskList.get(i));
        }
    }

    public static void printGoodbyeMessage() {
        String end = """
                     _________________________________
                     Goodbye. See you!
                     _________________________________
                     """;
        System.out.println(end);

    }

    public static void printAddTaskMessage(ArrayList<Task> taskList) {
        int numOfTask = taskList.size();
        System.out.println( "_________________________________" + "\n" +
                "Got it. I've added this task: " +
                taskList.get(numOfTask - 1) + "\n" +
                "Now you have "+ numOfTask +" tasks in the list." + "\n" +
                "_________________________________");
    };
}