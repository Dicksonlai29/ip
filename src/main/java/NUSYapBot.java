import java.util.Scanner;

public class NUSYapBot {
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
        while (flag) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();

            if (answer.equals("list")) {
                int i = 0;
                System.out.println("_________________________________");
                while (taskList[i] != null) {

                    System.out.println( i + 1 + ". " +
                                        (taskList[i].getIsCompleted() ? "[X] " : "[ ] ") +
                                        taskList[i].getTitle());
                    i++;
                }
                System.out.println("_________________________________");
            } else if (answer.equals("bye")) {
                flag = false;
                System.out.println(end);
            } else if (answer.startsWith("mark")) {
                int taskNum = Integer.parseInt(answer.split(" ")[1]) - 1;
                String taskTitle = taskList[taskNum].getTitle();
                taskList[taskNum].setIsCompleted(true);
                System.out.println( "_________________________________" + "\n" +
                                    "Nice! I've marked this task as done:" + "\n" + "[X] " +
                                    taskTitle + "\n" +
                                    "_________________________________");
            } else if (answer.startsWith("unmark")) {
                int taskNum = Integer.parseInt(answer.split(" ")[1]) - 1;
                String taskTitle = taskList[taskNum].getTitle();
                taskList[taskNum].setIsCompleted(false);
                System.out.println( "_________________________________" + "\n" +
                        "Nice! I've marked this task as not done yet:" + "\n" + "[ ] " +
                        taskTitle + "\n" +
                        "_________________________________");
            } else {
                    System.out.println("_________________________________" + "\n" +
                                       "added: " + answer + "\n" +
                                       "_________________________________");
                    //create new Task object
                    taskList[pointer] = new Task(answer);
                    pointer++;

            }
        }
    }
}
