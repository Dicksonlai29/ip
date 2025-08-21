import java.util.Scanner;

public class NUSYapBot {

    public static void printAddTaskMessage(Task[] taskList, int pointer){
        System.out.println( "_________________________________" + "\n" +
                "Got it. I've added this task: " +
                taskList[pointer-1] + "\n" +
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
        Task[] taskList = new Task[100];
        int pointer = 0;
        Scanner input = new Scanner(System.in);

        while (flag) {
            String answer = input.nextLine();

            if (answer.equals("list")) {
                int i = 0;
                System.out.println("_________________________________");
                while (taskList[i] != null) {

                    System.out.println( i + 1 + ". " + taskList[i]);
                    i++;
                }
                System.out.println("_________________________________");
            } else if (answer.equals("bye")) {
                flag = false;
                System.out.println(end);

            } else if (answer.startsWith("mark")) {
                try {
                    CommandHandler.markTask(taskList, answer, true);
                } catch (LackingInputException | MarkingException e) {
                    System.out.println(e);
                }

            } else if (answer.startsWith("unmark")) {
                try {
                    CommandHandler.markTask(taskList, answer, false);
                } catch (LackingInputException | MarkingException e) {
                System.out.println(e);
            }
            }
            else {
                try {
                    Task newTask = CommandHandler.createTask(answer);
                    taskList[pointer] = newTask;
                    pointer++;
                    printAddTaskMessage(taskList, pointer);
                } catch (LackingInputException | UnrecognisedCommandException e) {
                    System.out.println(e);
                }

            }
        }
    }
}
