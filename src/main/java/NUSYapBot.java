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
        String[] list = new String[100];
        int pointer = 0;
        while (flag) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();

            switch (answer) {
                case "list":
                    int i = 0;
                    System.out.println("_________________________________");
                    while (list[i] != null) {
                        System.out.println(i+1 + ". " + list[i]);
                        i++;
                    }
                    System.out.println("_________________________________");
                    break;
                case "bye":
                    flag = false;
                    System.out.println(end);
                    break;
                default:
                    System.out.println("_________________________________" + "\n" +
                                       "added: " + answer + "\n" +
                                       "_________________________________");
                    list[pointer] = answer;
                    pointer++;
                    break;
            }
        }
    }
}
