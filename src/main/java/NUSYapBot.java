import java.util.ArrayList;
import java.util.Scanner;

public class NUSYapBot {

    public static void printAddTaskMessage(ArrayList<Task> taskList, int pointer){
        System.out.println( "_________________________________" + "\n" +
                "Got it. I've added this task: " +
                taskList.get(pointer - 1) + "\n" +
                "Now you have "+ pointer +" tasks in the list." + "\n" +
                "_________________________________");
    };

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
        ArrayList<Task> taskList = new ArrayList<>();
        int pointer = 0;
        Scanner input = new Scanner(System.in);

        while (flag) {
            String answer = input.nextLine();

            if (answer.equals("list")) {
                System.out.println("_________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println( i + 1 + ". " + taskList.get(i));
                }
                System.out.println("_________________________________");
            } else if (answer.equals("bye")) {
                flag = false;
                System.out.println(end);

            } else if (answer.startsWith("mark")) {
                try {
                    CommandHandler.markTask(taskList, answer, true);
                } catch (LackingInputException | InvalidTaskException e) {
                    System.out.println(e);
                }

            } else if (answer.startsWith("unmark")) {
                try {
                    CommandHandler.markTask(taskList, answer, false);
                } catch (LackingInputException | InvalidTaskException e) {
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
                    printAddTaskMessage(taskList, pointer);
                } catch (LackingInputException | UnrecognisedCommandException e) {
                    System.out.println(e);
                }

            }
        }
    }
}
