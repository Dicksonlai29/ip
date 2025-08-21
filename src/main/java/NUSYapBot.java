import java.util.Scanner;

public class NUSYapBot {

    public static void PrintAddTaskMessage(Task[] taskList, int pointer){
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



        while (flag) {
            Scanner input = new Scanner(System.in);
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

            } else if (answer.startsWith("todo")) {
                //create ToDo object
                String taskTitle = answer.substring(5);
                taskList[pointer] = new ToDo(taskTitle);
                pointer++;

                PrintAddTaskMessage(taskList,pointer);


            } else if (answer.startsWith("deadline")) {
                //create Deadline object
                int indexOfBy = answer.indexOf("/by");
                int indexOfDeadline = indexOfBy + 4;
                String taskTitle = answer.substring(9, indexOfBy);
                String deadline = answer.substring(indexOfDeadline);
                taskList[pointer] = new Deadline(taskTitle, deadline);
                pointer++;

                PrintAddTaskMessage(taskList,pointer);

            } else if (answer.startsWith("event")) {
                //create Event object
                int indexOfFrom = answer.indexOf("/from");
                int indexOfStartDate = indexOfFrom + 6;

                int indexOfTo = answer.indexOf("/to");
                int indexOfEndDate = indexOfTo + 4;

                String taskTitle = answer.substring(6, indexOfFrom);
                String startDate = answer.substring(indexOfStartDate, indexOfTo);
                String endDate = answer.substring(indexOfEndDate);
                taskList[pointer] = new Event(taskTitle, startDate, endDate);
                pointer++;

                PrintAddTaskMessage(taskList,pointer);

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
            }
//            else {
//                    System.out.println("_________________________________" + "\n" +
//                                       "added: " + answer + "\n" +
//                                       "_________________________________");
//                    //create new Task object
//                    taskList[pointer] = new Task(answer);
//                    pointer++;
//
//            }
        }
    }
}
